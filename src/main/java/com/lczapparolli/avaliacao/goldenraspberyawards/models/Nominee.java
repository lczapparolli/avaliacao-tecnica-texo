package com.lczapparolli.avaliacao.goldenraspberyawards.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Contém os dados de uma indicação ao prêmio Golden Raspbery, identificando quando o filme foi ganhador do prêmio
 */
@Entity
public class Nominee {

    //region Campos

    /**
     * Identificador único da entidade
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ano em que o filme foi lançado e concorreu ao prêmio
     */
    private int year;
    
    /**
     * Título do filme indicado
     */
    private String title;
    
    /**
     * Lista de estúdios responsáveis pelo filme
     */
    @ManyToMany
    @JoinTable(
        name = "nominee_studio", 
        joinColumns = @JoinColumn(name = "nominee_id"), 
        inverseJoinColumns = @JoinColumn(name = "studio_id"))
    private List<Studio> studios;
    
    /**
     * Lista de produtores do filme
     */
    @ManyToMany
    @JoinTable(
        name = "nominee_producer", 
        joinColumns = @JoinColumn(name = "nominee_id"), 
        inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private List<Producer> producers;
    
    /**
     * Indica se o filme foi vencedor no ano
     */
    private boolean winner;

    //endregion

    //region Construtores

    /**
     * Inicializa o objeto sem preencher as propriedades
     */
    public Nominee() { /* Empty */ }

    /**
     * Inicializa o objeto preenchendo as propriedades, exceto o identificador único
     * @param year Ano de lançamento do filme
     * @param title Título do filme
     * @param studios Lista de estúdios
     * @param producers Lista de produtores
     * @param winner Indicação se foi vencedor do prêmio
     */
    public Nominee(int year, String title, List<Studio> studios, List<Producer> producers, boolean winner) {
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    /**
     * Inicializa o objeto preenchendo todas as propriedades
     * @param id Identificador único do registro
     * @param year Ano de lançamento do filme
     * @param title Título do filme
     * @param studios Lista de estúdios
     * @param producers Lista de produtores
     * @param winner Indicação se foi vencedor do prêmio
     */
    public Nominee(Long id, int year, String title, List<Studio> studios, List<Producer> producers, boolean winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }


    //endregion

    //region Getters/Setters

    /**
     * Retorna o identificador único do registro
     * @return Valor atual do campo
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Define o identificador único do registro
     * @param id Novo valor do campo
     */
    public void setId(Long id) {
        this.id = id;
    }

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
    public List<Studio> getStudios() {
        return this.studios;
    }

    /**
     * Define a lista de estúdios do filme
     * @param studios Novo valor do campo
     */
    public void setStudios(List<Studio> studios) {
        this.studios = studios;
    }

    /**
     * Retorna a lista de produtores do filme
     * @return Valor atual do campo
     */
    public List<Producer> getProducers() {
        return this.producers;
    }

    /**
     * Defina a lista de produtores do filme
     * @param producers Novo valor do campo
     */
    public void setProducers(List<Producer> producers) {
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

    //region Métodos sobrescritos
    
    @Override
    public String toString() {
        return new StringBuilder()
            .append("Nominee(")
            .append("id:").append(id)
            .append(",year:").append(year)
            .append(",title:").append(title)
            .append(",winner:").append(winner)
            .append(",studios:").append(studios)
            .append(",producers:").append(producers)
            .append(")")
            .toString();
    }

    @Override
    public boolean equals(Object nominee) {
        if (this == nominee) return true;
        if (nominee == null) return false;
        if (!(nominee instanceof Nominee)) return false;
        return this.id == ((Nominee) nominee).id;
    }

    //endregion

}