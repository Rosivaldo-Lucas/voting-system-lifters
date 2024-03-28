package com.rosivaldolucas.votingsystemback.domain.voto;

import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;
import com.rosivaldolucas.votingsystemback.domain.eleitor.Eleitor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "votos")
public class Voto {

  @EmbeddedId
  private IdVoto id;

  @ManyToOne
  @MapsId("id_candidato")
  @JoinColumn(name = "id_candidato")
  private Candidato candidato;

  @ManyToOne
  @MapsId("id_eleitor")
  @JoinColumn(name = "id_eleitor")
  private Eleitor eleitor;

  @Column(name = "data")
  private LocalDateTime data;

}
