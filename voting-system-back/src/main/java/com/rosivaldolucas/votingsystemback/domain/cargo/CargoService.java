package com.rosivaldolucas.votingsystemback.domain.cargo;

import com.rosivaldolucas.votingsystemback.api.cargo.dto.NovoCargoInput;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CargoService {

  private final CargoRepository cargoRepository;

  private CargoService(final CargoRepository cargoRepository) {
    this.cargoRepository = cargoRepository;
  }

  public Cargo cadastrar(final NovoCargoInput input) {
    final Cargo cargo = Cargo.criarCom(input.nome());

    return this.cargoRepository.save(cargo);
  }

}
