package com.lczapparolli.avaliacao.goldenraspberyawards;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lczapparolli.avaliacao.goldenraspberyawards.controllers.ReportController;
import com.lczapparolli.avaliacao.goldenraspberyawards.data.CSVDataReader;
import com.lczapparolli.avaliacao.goldenraspberyawards.data.IRepository;
import com.lczapparolli.avaliacao.goldenraspberyawards.models.ProducersByIntervalReport;

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

    //endregion

    //region Campos

    /**
     * Repositório da aplicação, contendo os dados necessários
     */
    IRepository repository;

    //endregion

    //region Construtor

    /**
     * Construtor da aplicação, inicializa os objetos necessários
     */
    public GoldenRaspberyAwardsApplication() {
        this.inicializarRepositorio();
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
        if (this.repository != null)
            return new ResponseEntity<String>("Rest server is running", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Repository could not be initialized", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Consulta de produtores por intervalo entre as vitórias. Listando os produtores com maior e menor intervalo
     * @return Retorna os dados do relatório
     */
    @GetMapping("/reports/producers/byInterval")
    public ResponseEntity<ProducersByIntervalReport> getProducersByInterval() {
        //Inicializa o controller, passando o repositório como parâmetro
        ReportController controller = new ReportController(this.repository);
        //Obtém os dados do relatório
        ProducersByIntervalReport result = controller.getProducersByInterval();
        //Retorna os dados com status OK
        return new ResponseEntity<ProducersByIntervalReport>(result, HttpStatus.OK);
    }

    //endregion

    //region Métodos auxiliares

    /**
     * Inicializa o repositório de dados
     */
    private void inicializarRepositorio() {
        try {
            //Cria uma nova instância do repositório
            this.repository = new CSVDataReader();
        } catch (IOException e) {
            //Caso ocorra algum erro, deixa o repositório nulo
            this.repository = null;
        }
    }

    //endregion
}
