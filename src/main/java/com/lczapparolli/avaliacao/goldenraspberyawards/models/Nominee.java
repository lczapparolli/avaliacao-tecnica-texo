package com.lczapparolli.avaliacao.goldenraspberyawards.models;

import java.util.List;

/**
 * Contém os dados de uma indicação ao prêmio Golden Raspbery, identificando quando o filme foi ganhador do prêmio
 */
public class Nominee {

    //region Campos

    /**
     * Ano em que o filme foi lançado e concorreu ao prêmio
     */
    private int year;
    
    /**
     * Título do filme indicado
     */
    private String title;
    
    /**
     * Lista de nomes dos estúdios
     */
    private List<String> studios;
    
    /**
     * Lista de produtores do filme
     */
    private List<String> producers;
    
    /**
     * Indica se o filme foi vencedor no ano
     */
    private boolean winner;

    //endregion

    //region Getters/Setters

    /**
     * Retorna o ano em que o filme foi lançado
     * @return Valor atual do campo
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Define o valor do ano em que o filme foi lançado
     * @param year Novo valor do campo
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Retorna o título do filme
     * @return Valor atual do campo
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Define o valor do título do filme
     * @param title Novo valor do campo
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retorna a lista de estúdios do filme
     * @return Valor atual do campo
     */
    public List<String> getStudios() {
        return this.studios;
    }

    /**
     * Define a lista de estúdios do filme
     * @param studios Novo valor do campo
     */
    public void setStudios(List<String> studios) {
        this.studios = studios;
    }

    /**
     * Retorna a lista de produtores do filme
     * @return Valor atual do campo
     */
    public List<String> getProducers() {
        return this.producers;
    }

    /**
     * Defina a lista de produtores do filme
     * @param producers Novo valor do campo
     */
    public void setProducers(List<String> producers) {
        this.producers = producers;
    }

    /**
     * Retorna verdadeiro caso o filme tenha sido vencedor do prêmio
     * @return Valor atual do campo
     */
    public boolean getWinner() {
        return this.winner;
    }

    /**
     * Define se o filme foi vencedor do prêmio
     * @param winner Novo valor do campo
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    //endregion

}