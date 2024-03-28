package com.rosivaldolucas.votingsystemback.domain.cargo.exception;

public class CargoNaoEncontradoException extends RuntimeException {

  private final String mensagem;

  public CargoNaoEncontradoException(final String mensagem) {
    super();
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

}
