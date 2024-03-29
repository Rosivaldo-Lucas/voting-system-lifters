package com.rosivaldolucas.votingsystemback.domain.candidato;

import com.rosivaldolucas.votingsystemback.domain.exception.VotoDuplicadoException;
import com.rosivaldolucas.votingsystemback.domain.voto.Voto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class CandidatoRecebeVotoService {

  private final CandidatoRepository candidatoRepository;

  public CandidatoRecebeVotoService(CandidatoRepository candidatoRepository) {
    this.candidatoRepository = candidatoRepository;
  }

  @CacheEvict(value = "relatorioCandidatos", allEntries = true)
  public void receber(final Candidato candidato, final Voto voto) {
    this.validarVotoDuplicado(candidato, voto);

    candidato.receberVoto(voto);

    this.candidatoRepository.save(candidato);
  }

  private void validarVotoDuplicado(final Candidato candidato, final Voto voto) {
    candidato.getVotos().forEach((votoRecebido) -> {
      final boolean isEleitorJaVotou = votoRecebido.getEleitor().getId().equals(voto.getEleitor().getId());
      if (isEleitorJaVotou) {
        throw new VotoDuplicadoException(String.format("Eleitor de 'id' %s já votou neste Candidato", votoRecebido.getEleitor().getId()));
      }
    });
  }

}
