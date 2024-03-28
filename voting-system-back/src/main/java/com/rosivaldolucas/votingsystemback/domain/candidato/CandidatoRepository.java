package com.rosivaldolucas.votingsystemback.domain.candidato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {

  default Optional<Candidato> findById(final String idCandidato) {
    try {
      final UUID idCandidatoUUID = UUID.fromString(idCandidato);

      return findById(idCandidatoUUID);
    } catch (final IllegalArgumentException ex) {
      return Optional.empty();
    }
  }

}
