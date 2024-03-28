package com.rosivaldolucas.votingsystemback.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, UUID> {

  default Optional<T> findById(final String id) {
    try {
      final UUID idUUID = UUID.fromString(id);

      return findById(idUUID);
    } catch (final IllegalArgumentException ex) {
      return Optional.empty();
    }
  }

}
