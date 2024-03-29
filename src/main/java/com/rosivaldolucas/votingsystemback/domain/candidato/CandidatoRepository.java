package com.rosivaldolucas.votingsystemback.domain.candidato;

import com.rosivaldolucas.votingsystemback.domain.entity.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatoRepository extends BaseRepository<Candidato> {

  List<Candidato> findAllByDeletadoEmIsNullAndCargoNome(final String cargoNome);

  @Query("SELECT c FROM Candidato c WHERE (c.cargo, c.quantidadeVotos) IN (SELECT c2.cargo, MAX(c2.quantidadeVotos) FROM Candidato c2 GROUP BY c2.cargo)")
  List<Candidato> encontrarCandidatosVencedoresPorCategoria();

  Optional<Candidato> findByNumero(final Integer numero);

}
