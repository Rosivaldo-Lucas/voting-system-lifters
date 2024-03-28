package com.rosivaldolucas.votingsystemback.domain.exception;

public class DomainException extends RuntimeException {

  private final String mensagem;

  public DomainException(final String mensagem) {
    super();
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

}
