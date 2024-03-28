package com.rosivaldolucas.votingsystemback.api.cargo;

import com.rosivaldolucas.votingsystemback.api.cargo.dto.CargoOutput;
import com.rosivaldolucas.votingsystemback.api.cargo.dto.NovoCargoInput;
import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;
import com.rosivaldolucas.votingsystemback.domain.cargo.CargoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargos")
public class CargoController {

  private final CargoService cargoService;

  public CargoController(final CargoService cargoService) {
    this.cargoService = cargoService;
  }

  @GetMapping("/{idCargo}")
  public ResponseEntity<CargoOutput> buscarPorId(@PathVariable final String idCargo) {
    final Cargo cargo = this.cargoService.buscarPorId(idCargo);

    final CargoOutput cargoOutput = new CargoOutput(cargo);

    return ResponseEntity.status(HttpStatus.OK).body(cargoOutput);
  }

  @PostMapping
  public ResponseEntity<CargoOutput> cadastrar(@RequestBody @Validated final NovoCargoInput input) {
    final Cargo cargoCadastrado = this.cargoService.cadastrar(input);

    final CargoOutput cargoOutput = new CargoOutput(cargoCadastrado);

    return ResponseEntity.status(HttpStatus.CREATED).body(cargoOutput);
  }

}
