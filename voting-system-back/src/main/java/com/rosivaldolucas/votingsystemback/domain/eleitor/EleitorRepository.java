package com.rosivaldolucas.votingsystemback.domain.eleitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EleitorRepository extends JpaRepository<Eleitor, UUID> {

}
