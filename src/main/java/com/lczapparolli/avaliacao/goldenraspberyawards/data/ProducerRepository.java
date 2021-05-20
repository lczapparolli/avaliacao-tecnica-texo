package com.lczapparolli.avaliacao.goldenraspberyawards.data;

import com.lczapparolli.avaliacao.goldenraspberyawards.models.Producer;

import org.springframework.data.repository.CrudRepository;

/**
 * Repositório para manipulação dos dados de produtores
 */
public interface ProducerRepository extends CrudRepository<Producer, Long> {
    
    /**
     * Retorna um produtor, pesquisando pelo nome
     * @param name Nome do produtor a ser procurado
     * @return Retorna o registro encontrado ou `null` caso não encontre nenhum registro
     */
    Producer findByName(String name);

}
