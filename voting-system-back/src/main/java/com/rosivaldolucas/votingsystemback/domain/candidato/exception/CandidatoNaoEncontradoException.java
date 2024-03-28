package com.rosivaldolucas.votingsystemback.domain.candidato.exception;

public class CandidatoNaoEncontradoException extends RuntimeException {

  private final String mensagem;

  public CandidatoNaoEncontradoException(final String mensagem) {
    super();
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

}
