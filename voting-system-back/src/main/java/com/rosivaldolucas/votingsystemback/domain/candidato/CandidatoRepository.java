package com.rosivaldolucas.votingsystemback.domain.candidato;

import com.rosivaldolucas.votingsystemback.domain.entity.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatoRepository extends BaseRepository<Candidato> {

  Optional<Candidato> findByNumero(final Integer numero);

}
