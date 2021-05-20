package com.lczapparolli.avaliacao.goldenraspberyawards.data;

import com.lczapparolli.avaliacao.goldenraspberyawards.models.Nominee;

import org.springframework.data.repository.CrudRepository;

/**
 * Repositório para manipulação dos dados de indicações
 */
public interface NomineeRepository extends CrudRepository<Nominee, Long> {
    
}
