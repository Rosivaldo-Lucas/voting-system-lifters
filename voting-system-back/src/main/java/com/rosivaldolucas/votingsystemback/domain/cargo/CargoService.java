package com.rosivaldolucas.votingsystemback.domain.cargo;

import com.rosivaldolucas.votingsystemback.api.cargo.dto.AtualizarCargoInput;
import com.rosivaldolucas.votingsystemback.api.cargo.dto.NovoCargoInput;
import com.rosivaldolucas.votingsystemback.domain.cargo.exception.CargoDuplicadoException;
import com.rosivaldolucas.votingsystemback.domain.cargo.exception.CargoNaoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CargoService {

  private final CargoRepository cargoRepository;

  private CargoService(final CargoRepository cargoRepository) {
    this.cargoRepository = cargoRepository;
  }

  public Page<Cargo> listar(int numeroPagina, int tamanhoPagina) {
    return this.cargoRepository.findAllByDeletadoEmIsNull(PageRequest.of(numeroPagina, tamanhoPagina));
  }

  public Cargo buscarPorId(final String idCargo) {
    return this.cargoRepository
            .findById(idCargo)
            .orElseThrow(() -> new CargoNaoEncontradoException(String.format("Cargo de 'id' %s não encontrado.", idCargo)));
  }

  public Cargo cadastrar(final NovoCargoInput input) {
    final Cargo cargoASerCadastrado = Cargo.criarCom(input.nome());

    return this.cargoRepository.save(cargoASerCadastrado);
  }

  public Cargo atualizar(final String idCargo, final AtualizarCargoInput input) {
    final Cargo cargoASerAtualizado = this.buscarPorId(idCargo);

    this.validarDuplicidade(input.nome(), cargoASerAtualizado.getNome());

    cargoASerAtualizado.atualizar(input.nome());

    return this.cargoRepository.save(cargoASerAtualizado);
  }

  public void deletar(final String idCargo) {
    final Cargo cargoASerDeletado = this.buscarPorId(idCargo);

    cargoASerDeletado.deletar();

    this.cargoRepository.save(cargoASerDeletado);
  }

  private void validarDuplicidade(final String nomeCargoInput, final String nomeCargo) {
    final Optional<Cargo> cargoBuscadoPorNome = this.cargoRepository.findByNome(nomeCargoInput.toUpperCase());

    if (cargoBuscadoPorNome.isPresent() && !cargoBuscadoPorNome.get().getNome().equals(nomeCargo)) {
      throw new CargoDuplicadoException(String.format("O nome do cargo '%s' já existe.", nomeCargoInput));
    }
  }

}
