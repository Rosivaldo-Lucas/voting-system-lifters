package com.rosivaldolucas.votingsystemback.domain.candidato;

import com.rosivaldolucas.votingsystemback.domain.exception.VotoDuplicadoException;
import com.rosivaldolucas.votingsystemback.domain.voto.Voto;
import org.springframework.stereotype.Service;

@Service
public class CandidatoReceberVotoService {

  private final CandidatoRepository candidatoRepository;

  public CandidatoReceberVotoService(CandidatoRepository candidatoRepository) {
    this.candidatoRepository = candidatoRepository;
  }

  public void receber(final Candidato candidato, final Voto voto) {
    this.validarVotoDuplicado(candidato, voto);

    candidato.receberVoto(voto);

    this.candidatoRepository.save(candidato);
  }

  private void validarVotoDuplicado(final Candidato candidato, final Voto voto) {
    candidato.getVotos().forEach((votoRecebido) -> {
      final boolean isEleitorJaVotou = votoRecebido.getEleitor().getId().equals(voto.getEleitor().getId());
      if (isEleitorJaVotou) {
        throw new VotoDuplicadoException(String.format("Eleitor de 'id' %s já votou", votoRecebido.getEleitor().getId()));
      }
    });
  }

}
