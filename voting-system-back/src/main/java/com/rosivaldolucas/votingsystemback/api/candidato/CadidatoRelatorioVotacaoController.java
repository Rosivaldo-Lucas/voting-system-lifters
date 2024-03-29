package com.rosivaldolucas.votingsystemback.api.candidato;

import com.rosivaldolucas.votingsystemback.api.candidato.dto.CandidatoVencedorOutput;
import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;
import com.rosivaldolucas.votingsystemback.domain.candidato.CandidatoRelatorioVotacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CadidatoRelatorioVotacaoController {

  private final CandidatoRelatorioVotacaoService candidatoRelatorioVotacaoService;

  public CadidatoRelatorioVotacaoController(CandidatoRelatorioVotacaoService candidatoRelatorioVotacaoService) {
    this.candidatoRelatorioVotacaoService = candidatoRelatorioVotacaoService;
  }

  @GetMapping("/relatorio")
  public ResponseEntity<List<CandidatoVencedorOutput>> relatorio() {
    final List<Candidato> candidatosVencedores = this.candidatoRelatorioVotacaoService.gerar();

    final List<CandidatoVencedorOutput> candidatosVencedoresOutput = CandidatoVencedorOutput.criar(candidatosVencedores);

    return ResponseEntity.status(HttpStatus.OK).body(candidatosVencedoresOutput);
  }

}
