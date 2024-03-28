package com.rosivaldolucas.votingsystemback.domain.cargo.exception;

public class CargoDuplicadoException extends RuntimeException {

  private final String mensagem;

  public CargoDuplicadoException(final String mensagem) {
    super();
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

}
