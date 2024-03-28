package com.rosivaldolucas.votingsystemback.domain.eleitor;

import com.rosivaldolucas.votingsystemback.api.eleitor.dto.VotoEleitorInput;
import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;
import com.rosivaldolucas.votingsystemback.domain.candidato.CandidatoService;
import org.springframework.stereotype.Service;

@Service
public class VotoEleitorService {

  private final CandidatoService candidatoService;
  private final EleitorService eleitorService;

  public VotoEleitorService(final CandidatoService candidatoService, final EleitorService eleitorService) {
    this.candidatoService = candidatoService;
    this.eleitorService = eleitorService;
  }

  public void votar(final VotoEleitorInput input) {
    final Candidato candidatoAReceberVoto = this.candidatoService.buscarPorId(input.idCandidato());
  }

}
