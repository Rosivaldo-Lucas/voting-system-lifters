package com.rosivaldolucas.votingsystemback.domain.voto;

import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;
import com.rosivaldolucas.votingsystemback.domain.eleitor.Eleitor;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "votos")
public class Voto implements Serializable {

  @EmbeddedId
  private IdVoto id;

  @ManyToOne
  @MapsId("idCandidato")
  @JoinColumn(name = "id_candidato")
  private Candidato candidato;

  @ManyToOne
  @MapsId("idEleitor")
  @JoinColumn(name = "id_eleitor")
  private Eleitor eleitor;

  @Column(name = "data")
  private LocalDateTime data;

  protected Voto() { }

  private Voto(final IdVoto id, final Candidato candidato, final Eleitor eleitor) {
    this.id = id;
    this.candidato = candidato;
    this.eleitor = eleitor;
    this.data = LocalDateTime.now();
  }

  public static Voto criar(final Candidato candidato, final Eleitor eleitor) {
    final IdVoto id = IdVoto.criar(candidato.getId(), eleitor.getId());

    return new Voto(id, candidato, eleitor);
  }

  public IdVoto getId() {
    return id;
  }

  public Candidato getCandidato() {
    return candidato;
  }

  public Eleitor getEleitor() {
    return eleitor;
  }

  public LocalDateTime getData() {
    return data;
  }

}
