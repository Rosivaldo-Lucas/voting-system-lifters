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

}
