package com.rosivaldolucas.votingsystemback.domain.exception;

public class EntidadeDuplicadaException extends RuntimeException {

  private final String mensagem;

  public EntidadeDuplicadaException(final String mensagem) {
    super();
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

}
