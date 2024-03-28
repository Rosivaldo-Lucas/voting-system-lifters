package com.rosivaldolucas.votingsystemback.api.eleitor;

import com.rosivaldolucas.votingsystemback.api.eleitor.dto.EleitorOutput;
import com.rosivaldolucas.votingsystemback.api.eleitor.dto.NovoEleitorInput;
import com.rosivaldolucas.votingsystemback.domain.eleitor.Eleitor;
import com.rosivaldolucas.votingsystemback.domain.eleitor.EleitorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eleitores")
public class EleitorController {

  private final EleitorService eleitorService;

  public EleitorController(final EleitorService eleitorService) {
    this.eleitorService = eleitorService;
  }

  @PostMapping
  public ResponseEntity<EleitorOutput> cadastrar(@RequestBody @Validated final NovoEleitorInput input) {
    final Eleitor eleitorCadastrado = this.eleitorService.cadastrar(input);

    final EleitorOutput eleitorOutput = EleitorOutput.criar(eleitorCadastrado);

    return ResponseEntity.status(HttpStatus.CREATED).body(eleitorOutput);
  }

}
