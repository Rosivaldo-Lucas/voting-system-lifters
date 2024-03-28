package com.rosivaldolucas.votingsystemback.api.cargo;

import com.rosivaldolucas.votingsystemback.api.cargo.dto.CargoOutput;
import com.rosivaldolucas.votingsystemback.api.cargo.dto.NovoCargoInput;
import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;
import com.rosivaldolucas.votingsystemback.domain.cargo.CargoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cargos")
public class CargoController {

  private final CargoService cargoService;

  public CargoController(final CargoService cargoService) {
    this.cargoService = cargoService;
  }

  @PostMapping
  public ResponseEntity<CargoOutput> cadastrar(@RequestBody @Validated final NovoCargoInput input) {
    final Cargo cargoCadastrado = this.cargoService.cadastrar(input);

    final CargoOutput cargoOutput = new CargoOutput(cargoCadastrado);

    return ResponseEntity.status(HttpStatus.CREATED).body(cargoOutput);
  }

}
