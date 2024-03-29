package com.rosivaldolucas.votingsystemback.domain.exception;

public class VotoDuplicadoException extends RuntimeException {

  private final String mensagem;

  public VotoDuplicadoException(final String mensagem) {
    super();
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

}
