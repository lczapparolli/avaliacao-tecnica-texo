package com.lczapparolli.avaliacao.goldenraspberyawards.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ResourceUtils;

import com.lczapparolli.avaliacao.goldenraspberyawards.models.Nominee;

/**
 * Realiza a leitura da base de dados de indicações e vencedores da premiação
 */
public class CSVDataReader {

    //region Campos

    /**
     * Indicação do caminho do arquivo de dados
     */
    static final String DATA_FILE = "classpath:static/movielist.csv";
    /**
     * Lista de indicações e vencedores da premiação
     */
    private List<Nominee> nomineeList;

    //endregion

    //region Construtor

    /**
     * Inicializa a base de dados, executando a leitura do arquivo e desserialização dos dados
     * 
     * @throws FileNotFoundException Exceção disparada quando não houver arquivo de dados
     * @throws IOException Exceção disparada quando houver algum erro na leitura dos dados
     */
    public CSVDataReader() throws FileNotFoundException, IOException {
        // Retorna o arquivo referente ao recurso
        File dataFile = ResourceUtils.getFile(CSVDataReader.DATA_FILE);
        //Executa a leitura do arquivo
        this.nomineeList = this.readFile(dataFile);
    }

    //endregion

    //region Getters/Setters

    /**
     * Retorna a lista de indicações
     * @return Lista de indicações lida do arquivo de dados
     */
    public List<Nominee> getNomineeList() {
        return this.nomineeList;
    }

    //endregion

    //region Métodos auxiliares

    /**
     * Realiza a leitura das linhas do arquivo, realizando a desserialização dos objetos
     * 
     * @param dataFile Referência para o arquivo contendo os dados a serem lidos
     * @return Retorna a lista de indicações carregadas do arquivo
     * @throws IOException Exceção disparada quando houver algum problema ao ler o arquivo
     */
    private List<Nominee> readFile(File dataFile) throws IOException {
        //Inicializa a leitura do arquivo
        BufferedReader reader = new BufferedReader(new FileReader(dataFile));
        try {
            //Inicializa a lista que será retornada
            List<Nominee> result = new ArrayList<Nominee>();

            //Realiza a leitura da primeira linha
            String line = reader.readLine();
            //Executa enquanto houver dados no arquivo
            while (line != null) {
                //Desserializa a linha no objeto esperado
                Nominee nominee = this.parseLine(line);
                //Caso o objeto tenha sido retornado, adiciona na lista do resultado
                if (nominee != null)
                    result.add(nominee);
                
                //Realiza a leitura da próxima linha
                line = reader.readLine();
            }

            return result;
        } finally {
            //Fecha a leitura do arquivo
            reader.close();
        }
    }

    /**
     * Realiza a desserialização da linha do arquivo de dados no objeto esperado
     * @param line Conteúdo da linha do arquivo
     * @return Retorna o objeto gerado a partir da linha
     */
    private Nominee parseLine(String line) {
        // Caso a linha esteja nula
        if (line == null)
            return null;

        // Realiza o parse da linha
        String[] items = line.split(";");

        // Ignora caso a linha não tenha a quantidade de colunas esperada
        if (items.length < 4 || items.length > 5)
            return null;

        // Ignora caso a linha seja o cabeçalho
        if (items[0].toLowerCase().equals("year"))
            return null;

        //Instanciando objeto modelo e definindo valores
        Nominee result = new Nominee();
        result.setYear(Integer.parseInt(items[0]));
        result.setTitle(items[1]);
        result.setStudios(this.parseListField(items[2]));
        result.setProducers(this.parseListField(items[3]));
        if (items.length == 5)
            result.setWinner(items[4].toLowerCase().equals("yes"));
        else
            result.setWinner(false);
        return result;
    }

    /**
     * Trata as colunas que possuem mais de um valor
     * @param data Conteúdo do campo a ser tratado
     * @return Retorna a lista de valores presentes no campo
     */
    private List<String> parseListField(String data) {
        //Quebra o conteúdo do campo em valores
        String[] items = data.split("(,| and )");
        //Inicializa a lista de resultado
        List<String> result = new ArrayList<String>();
        //Adiciona os itens na lista, limpando os espaços em brando no início e no final do arquivo
        for (String item : items) {
            result.add(item.trim());
        }
        
        return result;
    }

    //endregion

}