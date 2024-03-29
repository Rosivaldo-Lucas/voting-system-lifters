package com.rosivaldolucas.votingsystemback.domain.eleitor;

import com.rosivaldolucas.votingsystemback.domain.entity.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleitorRepository extends BaseRepository<Eleitor> {

  Optional<Eleitor> findByCpf(final String cpf);

}
