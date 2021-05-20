package com.lczapparolli.avaliacao.goldenraspberyawards.data;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.lczapparolli.avaliacao.goldenraspberyawards.models.Nominee;
import com.lczapparolli.avaliacao.goldenraspberyawards.models.Producer;
import com.lczapparolli.avaliacao.goldenraspberyawards.models.Studio;

/**
 * Realiza a leitura da base de dados de indicações e vencedores da premiação
 */
@Component
public class CSVDataReader {

    //region Campos

    /**
     * Indicação do caminho do arquivo de dados
     */
    static final String DATA_FILE = "classpath:static/movielist.csv";

    /**
     * Repositório de dados para manipulação dos estúdios
     */
    private StudioRepository studioRepository;

    /**
     * Repositório de dados para manipulação dos produtores
     */
    private ProducerRepository producerRepository;

    /**
     * Repositório de dados para manipulação das indicações
     */
    private NomineeRepository nomineeRepository;

    //endregion

    //region Construtor

    /**
     * Inicializa a base de dados, executando a leitura do arquivo e desserialização dos dados
     * @param studioRepository Repositório de dados para manipulação dos estúdios
     * @param producerRepository Repositório de dados para manipulação dos produtores
     * @param nomineeRepository Repositório de dados para manipulação das indicações
     */
    public CSVDataReader(StudioRepository studioRepository, ProducerRepository producerRepository, NomineeRepository nomineeRepository) {
        this.studioRepository = studioRepository;
        this.producerRepository = producerRepository;
        this.nomineeRepository = nomineeRepository;
    }

    //endregion

    //region Métodos públicos

    public void initDatabase() throws IOException {
        //Retorna o arquivo referente ao recurso
        File dataFile = ResourceUtils.getFile(CSVDataReader.DATA_FILE);
        //Executa a leitura do arquivo
        this.readFile(dataFile);
    }

    //endregion

    //region Métodos auxiliares

    /**
     * Realiza a leitura das linhas do arquivo, realizando a desserialização dos objetos
     * 
     * @param dataFile Referência para o arquivo contendo os dados a serem lidos
     * @throws IOException Exceção disparada quando houver algum problema ao ler o arquivo
     */
    private void readFile(File dataFile) throws IOException {
        //Inicializa a leitura do arquivo
        BufferedReader reader = new BufferedReader(new FileReader(dataFile));
        try {

            //Realiza a leitura da primeira linha
            String line = reader.readLine();
            //Executa enquanto houver dados no arquivo
            while (line != null) {
                //Desserializa a linha no objeto esperado
                this.parseLine(line);
                //Realiza a leitura da próxima linha
                line = reader.readLine();
            }
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
    private void parseLine(String line) {
        // Caso a linha esteja nula
        if (line == null)
            return;

        // Realiza o parse da linha
        String[] items = line.split(";");

        // Ignora caso a linha não tenha a quantidade de colunas esperada
        if (items.length < 4 || items.length > 5)
            return;

        // Ignora caso a linha seja o cabeçalho
        if (items[0].toLowerCase().equals("year"))
            return;

        //Instanciando objeto modelo e definindo valores
        Nominee result = new Nominee();
        result.setYear(Integer.parseInt(items[0]));
        result.setTitle(items[1]);
        result.setStudios(this.getStudios(this.parseListField(items[2])));
        result.setProducers(this.getProducers(this.parseListField(items[3])));
        if (items.length == 5)
            result.setWinner(items[4].toLowerCase().equals("yes"));
        else
            result.setWinner(false);
        //Salva o registro no banco de dados
        this.nomineeRepository.save(result);
    }

    /**
     * Verifica se os estúdios informados existem, recupera o registro do banco de dados e, caso não exista, cria um novo registro
     * @param names Lista de nomes dos estúdios
     * @return Retorna a lista com os registros do banco de dados correspondentes aos nomes recebidos
     */
    private List<Studio> getStudios(List<String> names) {
        //Inicializa a lista de resultado
        List<Studio> result = new ArrayList<>();
        //Percorre a lista de nomes recebida
        for (String name : names) {
            //Consulta o estúdio no banco de dados
            Studio studio = this.studioRepository.findByName(name);
            //Caso não exista, insere o registro
            if (studio == null)
                studio = this.studioRepository.save(new Studio(name));
            //Adiciona o estúdio na lista do resultado
            result.add(studio);
        }
        //Retorna a lista de estúdios
        return result;
    }

    /**
     * Verifica se os produtores informados existem, recupera o registro do banco de daods, e caso não exista, cria um novo registro
     * @param names Lista de nomes dos produtores
     * @return Retorna a lista com os registros od banco de dados correspondentes aos nomes recebidos
     */
    private List<Producer> getProducers(List<String> names) {
        //Inicializa a lista de resultado
        List<Producer> result = new ArrayList<>();
        //Percorre a lista de nomes recebida
        for (String name : names) {
            //Consulta o produtor no banco de dados
            Producer producer = this.producerRepository.findByName(name);
            //Caso não exista, insere o registro
            if (producer == null)
                producer = this.producerRepository.save(new Producer(name));
            //Adiciona o produtor na lista do resultado
            result.add(producer);
        }
        //Retorna a lista de produtores
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
            //Ignora o item, caso esteja em branco
            item = item.trim();
            if (!item.equals(""))
                result.add(item.trim());
        }
        
        return result;
    }

    //endregion

}