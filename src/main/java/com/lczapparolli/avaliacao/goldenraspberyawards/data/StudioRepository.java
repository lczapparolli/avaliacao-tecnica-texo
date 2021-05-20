package com.lczapparolli.avaliacao.goldenraspberyawards.data;

import com.lczapparolli.avaliacao.goldenraspberyawards.models.Studio;

import org.springframework.data.repository.CrudRepository;

/**
 * Repositório para manipulação dos dados de estúdios
 */
public interface StudioRepository extends CrudRepository<Studio, Long> {
    
    /**
     * Retorna um estúdio, pesquisando pelo nome
     * @param name Nome do estúdio a ser procurado
     * @return Retorna o registro encontrado ou `null` caso não encontre nenhum registro
     */
    Studio findByName(String name);

}
