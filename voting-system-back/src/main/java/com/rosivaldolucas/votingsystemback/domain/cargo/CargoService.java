package com.rosivaldolucas.votingsystemback.domain.cargo;

import com.rosivaldolucas.votingsystemback.api.cargo.dto.NovoCargoInput;
import com.rosivaldolucas.votingsystemback.domain.cargo.exception.CargoNaoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

  private final CargoRepository cargoRepository;

  private CargoService(final CargoRepository cargoRepository) {
    this.cargoRepository = cargoRepository;
  }

  public Page<Cargo> listar(int numeroPagina, int tamanhoPagina) {
    return this.cargoRepository.findAll(PageRequest.of(numeroPagina, tamanhoPagina));
  }

  public Cargo buscarPorId(final String idCargo) {
    return this.cargoRepository
            .findById(idCargo)
            .orElseThrow(() -> new CargoNaoEncontradoException(String.format("Cargo de 'id' %s não encontrado.", idCargo)));
  }

  public Cargo cadastrar(final NovoCargoInput input) {
    final Cargo cargo = Cargo.criarCom(input.nome());

    return this.cargoRepository.save(cargo);
  }

}
