package com.lczapparolli.avaliacao.goldenraspberyawards.controllers;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.lczapparolli.avaliacao.goldenraspberyawards.data.NomineeRepository;
import com.lczapparolli.avaliacao.goldenraspberyawards.models.Nominee;
import com.lczapparolli.avaliacao.goldenraspberyawards.models.Producer;
import com.lczapparolli.avaliacao.goldenraspberyawards.models.ProducerInterval;
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
    private NomineeRepository nomineeRepository;

    //endregion

    //region Construtores

    /**
     * Inicializa o controller do relatório
     * @param nomineeRepository Repositório de indicações
     */
    public ReportController(NomineeRepository nomineeRepository) {
        this.nomineeRepository = nomineeRepository;
    }

    //endregion

    //region Métodos HTTP

    /**
     * Retorna o relatório com os produtores que possuem o maior e o menor intervalo entre duas vitórias
     * @return Dados do relatório
     */
    @GetMapping("/reports/producers/byInterval")
    public ProducersByIntervalReport getProducersByInterval() {
        //Obtém a lista de produtores com os anos em que venceu o prêmio
        Map<String, List<Integer>> producersWins = this.getProducersWins();

        //Inicializando variáveis de controle
        int maxInterval = 0;
        int minInterval = Integer.MAX_VALUE;
        List<ProducerInterval> min = new ArrayList<>();
        List<ProducerInterval> max = new ArrayList<>();
        
        //Percorre a lista de produtores
        for (Map.Entry<String, List<Integer>> entry : producersWins.entrySet()) {
            List<Integer> wins = entry.getValue();
            //Somente processa se possuir mais de uma vitória
            if (wins.size() > 1) {
                //Ordena a lista de vitórias
                Collections.sort(wins);
                //Percorre a lista, comparando o ano (i) com o próximo (i + 1)
                for (int i = 0; i < wins.size() - 1; i ++) {
                    int previous = wins.get(i);
                    int following = wins.get(i + 1);
                    int interval = following - previous;
                    
                    //Caso o intervalo seja menor ou igual ao que já foi identificado anteriormente
                    if (interval <= minInterval) {
                        //Caso seja menor, reinicia a lista e redefine a variável de controle
                        if (interval < minInterval) {
                            minInterval = interval;
                            min = new ArrayList<>();
                        }
                        //adiciona os dados na lista
                        min.add(new ProducerInterval(entry.getKey(), interval, previous, following));
                    }

                    //Caso o intervalo seja maior ou igual ao que já foi identificado anteriormente
                    if (interval >= maxInterval) {
                        //Caso seja maior, reinicia a lista e redefine a variável de controle
                        if (interval > maxInterval) {
                            maxInterval = interval;
                            max = new ArrayList<>();
                        }
                        //Adiciona os dados na lista
                        max.add(new ProducerInterval(entry.getKey(), interval, previous, following));
                    }
                }
            }
        }
        
        //Retorna os dados do relatório
        return new ProducersByIntervalReport(min, max);
    }

    //endregion

    //region Métodos auxiliares

    /**
     * Retorna a lista com os anos das vitórias de cada produtor
     * @return Mapeamento do produtor com a lista de vitórias
     */
    private Map<String, List<Integer>> getProducersWins() {
        //Inicializa a lista de dados
        Map<String, List<Integer>> producersWins = new HashMap<>();
        //Percorre a lista com todas as indicações
        for (Nominee nominee : nomineeRepository.findAllByWinner(true)) {
            //Percorre a lista de produtores do filme
            for (Producer producer : nominee.getProducers()) {
                //Obtém a lista de vitórias do produtor
                List<Integer> wins = producersWins.get(producer.getName());
                //Caso a lista ainda não tenha sido inicializada, cria uma nova lista e adiciona no Map
                if (wins == null) {
                    wins = new ArrayList<>();
                    producersWins.put(producer.getName(), wins);
                }
                //Adiciona o ano da vitória na lista do produtor
                wins.add(nominee.getYear());
            }
        }
        //Retorna os dados preenchidos
        return producersWins;
    }

    //endregion
}
