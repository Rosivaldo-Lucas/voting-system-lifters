package com.rosivaldolucas.votingsystemback.domain.eleitor;

import com.rosivaldolucas.votingsystemback.api.eleitor.dto.VotoEleitorInput;
import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;
import com.rosivaldolucas.votingsystemback.domain.candidato.CandidatoReceberVotoService;
import com.rosivaldolucas.votingsystemback.domain.candidato.CandidatoService;
import com.rosivaldolucas.votingsystemback.domain.voto.Voto;
import org.springframework.stereotype.Service;

@Service
public class VotoEleitorService {

  private final CandidatoService candidatoService;
  private final EleitorService eleitorService;
  private final CandidatoReceberVotoService candidatoReceberVotoService;

  public VotoEleitorService(CandidatoService candidatoService, EleitorService eleitorService, CandidatoReceberVotoService candidatoReceberVotoService) {
    this.candidatoService = candidatoService;
    this.eleitorService = eleitorService;
    this.candidatoReceberVotoService = candidatoReceberVotoService;
  }

  public void votar(final String idEleitor, final VotoEleitorInput input) {
    this.validarIdEleitor(idEleitor, input.idEleitor());

    final Candidato candidatoAReceberVoto = this.candidatoService.buscarPorId(input.idCandidato());
    final Eleitor eleitorAVotar = this.eleitorService.busarPorId(input.idEleitor());

    final Voto votoCriado = Voto.criar(candidatoAReceberVoto, eleitorAVotar);

    this.candidatoReceberVotoService.receber(candidatoAReceberVoto, votoCriado);
  }

  private void validarIdEleitor(final String idEleitor, final String idEleitorInput) {
    if (!idEleitor.equals(idEleitorInput)) {
      throw new IllegalArgumentException("");
    }
  }

}
