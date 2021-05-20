package com.lczapparolli.avaliacao.goldenraspberyawards.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Contém os dados de um estúdio
 */
@Entity
public class Studio {
    
    //region Campos

    /**
     * Identificador único do registro
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do estúdio
     */
    private String name;

    //endregion

    //region Construtores

    /**
     * Inicializa o objeto sem definir nenhuma propriedade
     */
    public Studio() { /* Empty */}

    /**
     * Inicializa o objeto definido as propriedades, exceto o identificador único
     * @param name Nome do estúdio
     */
    public Studio(String name) {
        this.name = name;
    }

    /**
     * Inicializa o objeto definindo todas as propriedades
     * @param id Identificador único
     * @param name Nome do estúdio
     */
    public Studio(Long id, String name) {
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
     * Retorna o nome do estúdio
     * @return Valor atual
     */
    public String getName() {
        return this.name;
    }

    /**
     * Define o nome do estúdio
     * @param name Novo valor
     */
    public void setName(String name) {
        this.name = name;
    }

    //endregion

    //region Métodos sobrescritos
    
    @Override
    public String toString() {
        return new StringBuilder()
            .append("ProducerInterval(")
            .append("id:").append(id)
            .append(",name:").append(name)
            .append(")")
            .toString();
    }

    @Override
    public boolean equals(Object studio) {
        if (this == studio) return true;
        if (studio == null) return false;
        if (!(studio instanceof Studio)) return false;
        return this.id == ((Studio) studio).id;
    }

    //endregion

}
