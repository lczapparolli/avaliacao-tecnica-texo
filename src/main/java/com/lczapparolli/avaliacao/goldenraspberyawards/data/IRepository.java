package com.lczapparolli.avaliacao.goldenraspberyawards.data;

import java.util.List;

import com.lczapparolli.avaliacao.goldenraspberyawards.models.Nominee;

/**
 * Interface padrão para o repositório da aplicação.
 */
public interface IRepository {
    
    /**
     * Retorna a lista de indicações
     * @return Lista de indicações presente na base de dados
     */
    List<Nominee> getNomineeList();

}
