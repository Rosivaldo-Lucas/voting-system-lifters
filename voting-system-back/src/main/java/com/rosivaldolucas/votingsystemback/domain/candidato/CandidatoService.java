package com.rosivaldolucas.votingsystemback.domain.candidato;

import com.rosivaldolucas.votingsystemback.api.candidato.dto.AtualizarCandidatoInput;
import com.rosivaldolucas.votingsystemback.api.candidato.dto.NovoCandidatoInput;
import com.rosivaldolucas.votingsystemback.domain.candidato.exception.CandidatoNaoEncontradoException;
import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;
import com.rosivaldolucas.votingsystemback.domain.cargo.CargoService;
import com.rosivaldolucas.votingsystemback.domain.cargo.exception.CargoDuplicadoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatoService {

  private final CandidatoRepository candidatoRepository;
  private final CargoService cargoService;

  public CandidatoService(final CandidatoRepository candidatoRepository, final CargoService cargoService) {
    this.candidatoRepository = candidatoRepository;
    this.cargoService = cargoService;
  }

  public Page<Candidato> listar(int numeroPagina, int tamanhoPagina) {
    return this.candidatoRepository.findAllByDeletadoEmIsNull(PageRequest.of(numeroPagina, tamanhoPagina));
  }

  public Candidato buscarPorId(final String idCandidato) {
    return this.candidatoRepository
            .findById(idCandidato)
            .orElseThrow(() -> new CandidatoNaoEncontradoException(String.format("Candidato de 'id' %s não encontrado.", idCandidato)));
  }

  public Candidato cadastrar(final NovoCandidatoInput input) {
    final Cargo cargoCanditado = this.cargoService.buscarPorId(input.idCargo());

    final Candidato candidatoASerCadastrado = Candidato.criarCom(input.nome(), input.numero(), input.legenda(), cargoCanditado);

    return this.candidatoRepository.save(candidatoASerCadastrado);
  }

  public Candidato atualizar(final String idCandidato, final AtualizarCandidatoInput input) {
    final Candidato candidatoASerAtualizado = this.buscarPorId(idCandidato);

    this.validarDuplicidade(input.numero(), candidatoASerAtualizado.getNumero());

    final Cargo cargoAtualizado = this.cargoService.buscarPorId(input.idCargo());

    candidatoASerAtualizado.atualizar(input.nome(), input.numero(), input.legenda(), cargoAtualizado);

    return this.candidatoRepository.save(candidatoASerAtualizado);
  }

  public void deletar(final String idCandidato) {
    final Candidato candidatoASerDeletado = this.buscarPorId(idCandidato);

    candidatoASerDeletado.deletar();

    this.candidatoRepository.save(candidatoASerDeletado);
  }

  private void validarDuplicidade(final Integer numeroCandidadoInput, final Integer numeroCandidato) {
    final Optional<Candidato> candidatoBuscadoPorNumero = this.candidatoRepository.findByNumero(numeroCandidadoInput);

    if (candidatoBuscadoPorNumero.isPresent() && !candidatoBuscadoPorNumero.get().getNumero().equals(numeroCandidato)) {
      throw new CargoDuplicadoException(String.format("O cpf '%d' já existe.", numeroCandidadoInput));
    }
  }

}
