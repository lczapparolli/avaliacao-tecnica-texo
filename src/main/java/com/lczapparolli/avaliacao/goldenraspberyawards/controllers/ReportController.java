package com.lczapparolli.avaliacao.goldenraspberyawards.controllers;

import com.lczapparolli.avaliacao.goldenraspberyawards.data.ProducerIntervalRepository;
import com.lczapparolli.avaliacao.goldenraspberyawards.models.ProducersByIntervalReport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável por retornar os dados dos relatórios da aplicação
 */
@RestController
public class ReportController {
    
    //region Campos

    /**
     * Repositório de indicações
     */
    private ProducerIntervalRepository producerIntervalRepository;

    //endregion

    //region Construtores

    /**
     * Inicializa o controller do relatório
     * @param nomineeRepository Repositório de indicações
     */
    public ReportController(ProducerIntervalRepository producerIntervalRepository) {
        this.producerIntervalRepository = producerIntervalRepository;
    }

    //endregion

    //region Métodos HTTP

    /**
     * Retorna o relatório com os produtores que possuem o maior e o menor intervalo entre duas vitórias
     * @return Dados do relatório
     */
    @GetMapping("/reports/producers/byInterval")
    public ProducersByIntervalReport getProducersByInterval() {
        //Retorna os dados do relatório
        return new ProducersByIntervalReport(producerIntervalRepository.findAllWithMinInterval(), producerIntervalRepository.findAllWithMaxInterval());
    }

    //endregion

}
