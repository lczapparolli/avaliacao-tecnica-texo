package com.lczapparolli.avaliacao.goldenraspberyawards.models;

import java.util.List;

/**
 * Classe contendo os dados para o relatório de produtores por intervalo entre as vitórias
 */
public class ProducersByIntervalReport {
    
    //region Campos

    /**
     * Lista de produtores com o menor intervalo entre duas vitórias
     */
    private List<ProducerInterval> min;

    /**
     * Lista de produtores com o maior intervalo entre duas vitórias
     */
    private List<ProducerInterval> max;

    //endregion

    //region Construtores

    /**
     * Inicializa o objeto sem preencher as propriedades
     */
    public ProducersByIntervalReport() { /* Vazio */ }

    /**
     * Inicializa o objeto preenchendo as propriedades
     * @param min Lista de produtores com o menor intervalo entre duas vitórias
     * @param max Lista de produtores com o maior intervalo entre duas vitórias
     */
    public ProducersByIntervalReport(List<ProducerInterval> min, List<ProducerInterval> max) {
        this.min = min;
        this.max = max;
    }

    //endregion

    //region Getters/Setters

    /**
     * Retorna a lista de produtores com o menor intervalo entre duas vitórias
     * @return Valor atual
     */
    public List<ProducerInterval> getMin() {
        return min;
    }

    /**
     * Define a lista de produtores com o menor intervalo entre duas vitórias
     * @return Novo valor
     */
    public List<ProducerInterval> getMax() {
        return max;
    }
    
    /**
     * Retorna a lista de produtores com o maior intervalo entre duas vitórias
     * @param min Valor atual
     */
    public void setMin(List<ProducerInterval> min) {
        this.min = min;
    }
    
    /**
     * Define a lista de produtores com o maior intervalo entre duas vitórias
     * @param max Novo valor
     */
    public void setMax(List<ProducerInterval> max) {
        this.max = max;
    }

    //endregion

    //region Métodos sobrescritos
    
    @Override
    public String toString() {
        return new StringBuilder()
            .append("ProducersByIntervalReport(")
            .append("min:").append(min)
            .append(",max:").append(max)
            .append(")")
            .toString();
    }

    @Override
    public boolean equals(Object producersByIntervalReport) {
        if (this == producersByIntervalReport) return true;
        if (producersByIntervalReport == null) return false;
        if (!(producersByIntervalReport instanceof ProducersByIntervalReport)) return false;
        return this.min == ((ProducersByIntervalReport) producersByIntervalReport).min && 
            this.max == ((ProducersByIntervalReport) producersByIntervalReport).max;
    }

    //endregion

}
