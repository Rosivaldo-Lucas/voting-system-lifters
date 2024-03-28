package com.rosivaldolucas.votingsystemback.domain.eleitor;

import com.rosivaldolucas.votingsystemback.api.eleitor.dto.NovoEleitorInput;
import org.springframework.stereotype.Service;

@Service
public class EleitorService {

  private final EleitorRepository eleitorRepository;

  public EleitorService(final EleitorRepository eleitorRepository) {
    this.eleitorRepository = eleitorRepository;
  }

  public Eleitor cadastrar(final NovoEleitorInput input) {
    final Eleitor eleitorASerCadastrado = Eleitor.criarCom(input.nome(), input.cpf());

    return this.eleitorRepository.save(eleitorASerCadastrado);
  }

}
