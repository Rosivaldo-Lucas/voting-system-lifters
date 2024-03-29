package com.rosivaldolucas.votingsystemback.api.eleitor;

import com.rosivaldolucas.votingsystemback.api.eleitor.dto.AtualizarEleitorInput;
import com.rosivaldolucas.votingsystemback.api.eleitor.dto.EleitorOutput;
import com.rosivaldolucas.votingsystemback.api.eleitor.dto.NovoEleitorInput;
import com.rosivaldolucas.votingsystemback.domain.eleitor.Eleitor;
import com.rosivaldolucas.votingsystemback.domain.eleitor.EleitorService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eleitores")
public class EleitorController {

  private final EleitorService eleitorService;

  public EleitorController(final EleitorService eleitorService) {
    this.eleitorService = eleitorService;
  }

  @GetMapping
  public ResponseEntity<Page<EleitorOutput>> listar(@RequestParam final int numeroPagina, @RequestParam final int tamanhoPagina) {
    final Page<Eleitor> eleitoresPage = this.eleitorService.listar(numeroPagina, tamanhoPagina);

    final Page<EleitorOutput> eleitoresOutput = EleitorOutput.criar(eleitoresPage);

    return ResponseEntity.status(HttpStatus.OK).body(eleitoresOutput);
  }

  @GetMapping("/{idEleitor}")
  public ResponseEntity<EleitorOutput> buscarPorId(@PathVariable final String idEleitor) {
    final Eleitor eleitor = this.eleitorService.busarPorId(idEleitor);

    final EleitorOutput eleitorOutput = EleitorOutput.criar(eleitor);

    return ResponseEntity.status(HttpStatus.OK).body(eleitorOutput);
  }

  @PostMapping
  public ResponseEntity<EleitorOutput> cadastrar(@RequestBody @Validated final NovoEleitorInput input) {
    final Eleitor eleitorCadastrado = this.eleitorService.cadastrar(input);

    final EleitorOutput eleitorOutput = EleitorOutput.criar(eleitorCadastrado);

    return ResponseEntity.status(HttpStatus.CREATED).body(eleitorOutput);
  }

  @PatchMapping("/{idEleitor}")
  public ResponseEntity<EleitorOutput> atualizar(@PathVariable final String idEleitor, @RequestBody @Validated final AtualizarEleitorInput input) {
    final Eleitor eleitorAtualizado = this.eleitorService.atualizar(idEleitor, input);

    final EleitorOutput eleitorOutput = EleitorOutput.criar(eleitorAtualizado);

    return ResponseEntity.status(HttpStatus.OK).body(eleitorOutput);
  }

  @DeleteMapping("/{idEleitor}")
  public ResponseEntity<Void> deletar(@PathVariable final String idEleitor) {
    this.eleitorService.deletar(idEleitor);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
