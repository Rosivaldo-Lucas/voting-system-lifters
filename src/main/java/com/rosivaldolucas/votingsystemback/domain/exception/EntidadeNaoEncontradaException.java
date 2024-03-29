package com.rosivaldolucas.votingsystemback.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

  private final String mensagem;

  public EntidadeNaoEncontradaException(final String mensagem) {
    super();
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

}
