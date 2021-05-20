package com.lczapparolli.avaliacao.goldenraspberyawards;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lczapparolli.avaliacao.goldenraspberyawards.data.CSVDataReader;

/**
 * Classe principal da aplicação
 */
@SpringBootApplication
@RestController
public class GoldenRaspberyAwardsApplication {
    
    //region Inicializador

    /**
     * Ponto de entrada do programa, inicializa a aplicação
     * @param args Parâmetros de execução do programa
     */
    public static void main(String[] args) {
        SpringApplication.run(GoldenRaspberyAwardsApplication.class, args);
    }

    /**
     * Inicializa a aplicação, preenchendo os dados necessários no banco de dados
     * @param dataReader Classe responsável pela leitura do arquivo de dados
     * @return Retorna um objeto ComandLineRunner
     */
    @Bean
    public CommandLineRunner initApplication(CSVDataReader dataReader) {
        return (args) -> {
            //Realiza a leitura do arquivo, inserindo os dados no banco
            dataReader.initDatabase();
        };
    }

    //endregion

    //region Métodos HTTP

    /**
     * Raiz da aplicação, identifica se o serviço está executando ou se houve algum erro
     * @return Retorna o status HTTP com a mensagem correspondente
     */
    @GetMapping("/")
    public ResponseEntity<String> hello() {
        //Caso o repositório esteja inicializado
        return new ResponseEntity<String>("Rest server is running", HttpStatus.OK);
    }

    //endregion
}
