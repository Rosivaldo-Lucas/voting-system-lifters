package com.rosivaldolucas.votingsystemback.domain.voto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class IdVoto implements Serializable {

  @Column(name = "id_candidato")
  UUID idCandidato;

  @Column(name = "id_eleitor")
  UUID idEleitor;

  protected IdVoto() { }

  private IdVoto(final UUID idCandidato, final UUID idEleitor) {
    this.idCandidato = idCandidato;
    this.idEleitor = idEleitor;
  }

  public static IdVoto criar(final String idCandidato, final String idEleitor) {
    return new IdVoto(UUID.fromString(idCandidato), UUID.fromString(idEleitor));
  }

}
