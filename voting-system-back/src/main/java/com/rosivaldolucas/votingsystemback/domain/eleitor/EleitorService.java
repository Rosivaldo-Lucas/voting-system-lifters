package com.rosivaldolucas.votingsystemback.domain.eleitor;

import com.rosivaldolucas.votingsystemback.api.eleitor.dto.NovoEleitorInput;
import com.rosivaldolucas.votingsystemback.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

}
