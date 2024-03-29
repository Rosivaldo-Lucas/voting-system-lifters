package com.rosivaldolucas.votingsystemback.domain.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "criado_em")
  private LocalDateTime criadoEm;

  @Column(name = "atualizado_em")
  private LocalDateTime atualizadoEm;

  @Column(name = "deletado_em")
  private LocalDateTime deletadoEm;

  protected BaseEntity() {
    final LocalDateTime dataHoraAtual = LocalDateTime.now();

    this.criadoEm = dataHoraAtual;
    this.atualizadoEm = dataHoraAtual;
    this.deletadoEm = null;
  }

  public String getId() {
    return id.toString();
  }

  public LocalDateTime getCriadoEm() {
    return criadoEm;
  }

  public LocalDateTime getAtualizadoEm() {
    return atualizadoEm;
  }

  public LocalDateTime getDeletadoEm() {
    return deletadoEm;
  }

  protected void atualizadoEm() {
    this.atualizadoEm = LocalDateTime.now();
  }

  protected void deletadoEm() {
    this.deletadoEm = LocalDateTime.now();
  }

}
