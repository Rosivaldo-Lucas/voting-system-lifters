package com.rosivaldolucas.votingsystemback.api.candidato;

import com.rosivaldolucas.votingsystemback.api.candidato.dto.CandidatoOutput;
import com.rosivaldolucas.votingsystemback.api.candidato.dto.NovoCandidatoInput;
import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;
import com.rosivaldolucas.votingsystemback.domain.candidato.CandidatoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

  private final CandidatoService candidatoService;

  public CandidatoController(final CandidatoService candidatoService) {
    this.candidatoService = candidatoService;
  }

  @GetMapping
  public ResponseEntity<Page<CandidatoOutput>> listar(@RequestParam final int numeroPagina, @RequestParam final int tamanhoPagina) {
    final Page<Candidato> candidatosPage = this.candidatoService.listar(numeroPagina, tamanhoPagina);

    final Page<CandidatoOutput> candidatosOutputPage = CandidatoOutput.criar(candidatosPage);

    return ResponseEntity.status(HttpStatus.OK).body(candidatosOutputPage);
  }

  @GetMapping("/{idCandidato}")
  public ResponseEntity<CandidatoOutput> buscarPorId(@PathVariable final String idCandidato) {
    final Candidato candidatoBuscado = this.candidatoService.buscarPorId(idCandidato);

    final CandidatoOutput candidatoOutput = CandidatoOutput.criar(candidatoBuscado);

    return ResponseEntity.status(HttpStatus.OK).body(candidatoOutput);
  }

  @PostMapping
  public ResponseEntity<CandidatoOutput> cadastrar(@RequestBody @Validated final NovoCandidatoInput input) {
    final Candidato candidatoCadastrado = this.candidatoService.cadastrar(input);

    final CandidatoOutput candidatoOutput = CandidatoOutput.criar(candidatoCadastrado);

    return ResponseEntity.status(HttpStatus.CREATED).body(candidatoOutput);
  }

}
