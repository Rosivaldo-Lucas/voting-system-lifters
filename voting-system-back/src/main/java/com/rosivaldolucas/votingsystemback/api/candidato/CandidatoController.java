package com.rosivaldolucas.votingsystemback.api.candidato;

import com.rosivaldolucas.votingsystemback.api.candidato.dto.CandidatoOutput;
import com.rosivaldolucas.votingsystemback.api.candidato.dto.NovoCandidatoInput;
import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;
import com.rosivaldolucas.votingsystemback.domain.candidato.CandidatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

  private final CandidatoService candidatoService;

  public CandidatoController(final CandidatoService candidatoService) {
    this.candidatoService = candidatoService;
  }

  @PostMapping
  public ResponseEntity<CandidatoOutput> cadastrar(@RequestBody @Validated final NovoCandidatoInput input) {
    final Candidato candidatoCadastrado = this.candidatoService.cadastrar(input);

    final CandidatoOutput candidatoOutput = CandidatoOutput.criar(candidatoCadastrado);

    return ResponseEntity.status(HttpStatus.CREATED).body(candidatoOutput);
  }

}
