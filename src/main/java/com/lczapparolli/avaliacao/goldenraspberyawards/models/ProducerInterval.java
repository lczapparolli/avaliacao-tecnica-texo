package com.lczapparolli.avaliacao.goldenraspberyawards.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modelo representando o intervalo entre duas vitórios de um produtor
 */
@Entity
public class ProducerInterval {

    //region Campos

    /**
     * Identificador único
     */
    @Id
    @JsonIgnore
    private Long id;

    /**
     * Nome do produtor
     */
    private String producer;

    /**
     * Intervalo em anos entre as vitórias
     */
    private int interval;

    /**
     * Ano da vitória anterior
     */
    private int previousWin;

    /**
     * Ano da vitória seguinte
     */
    private int followingWin;

    //endregion

    //region Contrutores

    /**
     * Inicializa o objeto sem preencher as propriedades
     */
    public ProducerInterval() { /* Empty */ }

    /**
     * Inicializa o objeto preenchendo as propriedades, exceto o identificador único
     * @param producer Nome do produtor
     * @param interval Intervalo em anos entre as vitórias
     * @param previousWin Ano da vitória anterior
     * @param followingWin Ano da vitória seguinte
     */
    public ProducerInterval(String producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    /**
     * Inicializa o objeto preenchendo todas as propriedades
     * @param id Identificador único do produtor
     * @param producer Nome do produtor
     * @param interval Intervalo em anos entre as vitórias
     * @param previousWin Ano da vitória anterior
     * @param followingWin Ano da vitória seguinte
     */
    public ProducerInterval(Long id, String producer, int interval, int previousWin, int followingWin) {
        this.id = id;
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    //endregion

    //region Getters/Setters

    /**
     * Retorna o identificador único do produtor
     * @return Valor atual
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Define o identificador único do produtor
     * @param id Novo valor
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Retorna o nome do produtor
     * @return Valor atual
     */
    public String getProducer() {
        return producer;
    }
    
    /**
     * Define o nome do produtor
     * @param producer Novo valor
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }
    
    /**
     * Retorna o intervalo em anos entre as vitórias
     * @return Valor atual
     */
    public int getInterval() {
        return interval;
    }
    
    /**
     * Define o intervalo em anos entre as vitórias
     * @param interval Novo valor
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }
    
    /**
     * Retorna o ano da vitória anterior
     * @return Valor atual
     */
    public int getPreviousWin() {
        return previousWin;
    }
    
    /**
     * Define o ano da vitória anterior
     * @param previousWin Novo valor
     */
    public void setPreviousWin(int previousWin) {
        this.previousWin = previousWin;
    }
    
    /**
     * Retorna o ano da vitória seguinte
     * @return Valor atual
     */
    public int getFollowingWin() {
        return followingWin;
    }

    /**
     * Define o ano da vitória seguinte
     * @param followingWin Novo valor
     */
    public void setFollowingWin(int followingWin) {
        this.followingWin = followingWin;
    }

    //endregion

    //region Métodos sobrescritos
    
    @Override
    public String toString() {
        return new StringBuilder()
            .append("ProducerInterval(")
            .append("id:").append(id)
            .append(",producer:").append(producer)
            .append(",interval:").append(interval)
            .append(",previousWin:").append(previousWin)
            .append(",followingWin:").append(followingWin)
            .append(")")
            .toString();
    }

    @Override
    public boolean equals(Object producerInterval) {
        if (this == producerInterval) return true;
        if (producerInterval == null) return false;
        if (!(producerInterval instanceof ProducerInterval)) return false;
        return this.id == ((ProducerInterval) producerInterval).id &&
            this.interval == ((ProducerInterval) producerInterval).interval &&
            this.previousWin == ((ProducerInterval) producerInterval).previousWin &&
            this.followingWin == ((ProducerInterval) producerInterval).followingWin;
    }

    //endregion
    
}
