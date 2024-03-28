package com.rosivaldolucas.votingsystemback.domain.cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, UUID> {

  default Optional<Cargo> findById(final String idCargo) {
    try {
      final UUID idCargoUUID = UUID.fromString(idCargo);

      return findById(idCargoUUID);
    } catch (final IllegalArgumentException ex) {
      return Optional.empty();
    }
  }

  Optional<Cargo> findByNome(final String nome);

}
