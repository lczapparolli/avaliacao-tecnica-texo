package com.lczapparolli.avaliacao.goldenraspberyawards.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Contém os dados de um produtor
 */
@Entity
public class Producer {
    
    //region Campos

    /**
     * Identificador único do registro
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do produtor
     */
    private String name;

    //endregion

    //region Contrutores

    /**
     * Inicializa o objeto sem definir nenhuma propriedade
     */
    public Producer() { /* Empty */ }

    /**
     * Inicializa o objeto definindo as propriedades, exceto o identificador único
     * @param name Nome do produtor
     */
    public Producer(String name) {
        this.name = name;
    }
    
    /**
     * Inicializa o objeto definindo todas as propriedades
     * @param id Identificador único
     * @param name Nome do produtor
     */
    public Producer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //endregion

    //region Getters/Setters

    /**
     * Retorna o identificador único do registro
     * @return Valor atual
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Define o identificador único do registro
     * @param id Novo valor
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produtor
     * @return Valor atual
     */
    public String getName() {
        return this.name;
    }

    /**
     * Define o nome do produtor
     * @param name Novo valor
     */
    public void setName(String name) {
        this.name = name;
    }

    //enderegion

    //region Métodos sobrescritos
    
    @Override
    public String toString() {
        return new StringBuilder()
            .append("Producer(")
            .append("id:").append(id)
            .append(",name:").append(name)
            .append(")")
            .toString();
    }

    @Override
    public boolean equals(Object producer) {
        if (this == producer) return true;
        if (producer == null) return false;
        if (!(producer instanceof Producer)) return false;
        return this.id == ((Producer) producer).id;
    }

    //endregion
}
