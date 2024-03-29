package com.rosivaldolucas.votingsystemback.domain.exception;

public class VotosComputadosException extends RuntimeException {

  private final String mensagem;

  public VotosComputadosException(final String mensagem) {
    super();
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

}
