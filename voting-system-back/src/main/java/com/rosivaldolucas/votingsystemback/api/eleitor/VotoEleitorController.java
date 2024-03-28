package com.rosivaldolucas.votingsystemback.api.eleitor;

import com.rosivaldolucas.votingsystemback.api.eleitor.dto.VotoEleitorInput;
import com.rosivaldolucas.votingsystemback.domain.eleitor.VotoEleitorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eleitores")
public class VotoEleitorController {

  public final VotoEleitorService votoEleitorService;

  public VotoEleitorController(VotoEleitorService votoEleitorService) {
    this.votoEleitorService = votoEleitorService;
  }

  @PostMapping("/{idEleitor}/votar")
  public ResponseEntity<Void> votar(@PathVariable String idEleitor, @RequestBody @Validated final VotoEleitorInput input) {
    this.votoEleitorService.votar(idEleitor, input);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
