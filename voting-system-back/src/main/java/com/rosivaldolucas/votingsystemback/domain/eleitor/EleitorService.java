package com.rosivaldolucas.votingsystemback.domain.eleitor;

import com.rosivaldolucas.votingsystemback.api.eleitor.dto.AtualizarEleitorInput;
import com.rosivaldolucas.votingsystemback.api.eleitor.dto.NovoEleitorInput;
import com.rosivaldolucas.votingsystemback.domain.cargo.exception.CargoDuplicadoException;
import com.rosivaldolucas.votingsystemback.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EleitorService {

  private final EleitorRepository eleitorRepository;

  public EleitorService(final EleitorRepository eleitorRepository) {
    this.eleitorRepository = eleitorRepository;
  }

  public Page<Eleitor> listar(int numeroPagina, int tamanhoPagina) {
    return this.eleitorRepository.findAllByDeletadoEmIsNull(PageRequest.of(numeroPagina, tamanhoPagina));
  }

  public Eleitor busarPorId(final String idEleitor) {
    return this.eleitorRepository
            .findById(idEleitor)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Eleitor de 'id' %s não encontrado.", idEleitor)));
  }

  public Eleitor cadastrar(final NovoEleitorInput input) {
    final Eleitor eleitorASerCadastrado = Eleitor.criarCom(input.nome(), input.cpf());

    return this.eleitorRepository.save(eleitorASerCadastrado);
  }

  public Eleitor atualizar(final String idEleitor, final AtualizarEleitorInput input) {
    final Eleitor eleitorASerAtualizado = this.busarPorId(idEleitor);

    this.validarDuplicidade(input.cpf(), eleitorASerAtualizado.getCpf());

    eleitorASerAtualizado.atualizar(input.nome(), input.cpf());

    return this.eleitorRepository.save(eleitorASerAtualizado);
  }

  public void deletar(final String idEleitor) {
    final Eleitor eleitorASerDeletado = this.busarPorId(idEleitor);

    eleitorASerDeletado.deletar();

    this.eleitorRepository.save(eleitorASerDeletado);
  }

  private void validarDuplicidade(final String cpfEleitorInput, final String cpfEleitor) {
    final Optional<Eleitor> eleitorBuscadoPorCpf = this.eleitorRepository.findByCpf(cpfEleitorInput.toUpperCase());

    if (eleitorBuscadoPorCpf.isPresent() && !eleitorBuscadoPorCpf.get().getNome().equals(cpfEleitor)) {
      throw new CargoDuplicadoException(String.format("O cpf '%s' já existe.", cpfEleitorInput));
    }
  }

}
