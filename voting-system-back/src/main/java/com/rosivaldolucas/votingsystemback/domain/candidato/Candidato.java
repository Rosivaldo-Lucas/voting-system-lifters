package com.rosivaldolucas.votingsystemback.domain.candidato;

import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidatos")
public class Candidato {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "legenda")
  private String legenda;

  @ManyToOne
  @JoinColumn(name = "id_cargo")
  private Cargo cargo;

  @Column(name = "criado_em")
  private LocalDateTime criadoEm;

  @Column(name = "atualizado_em")
  private LocalDateTime atualizadoEm;

  @Column(name = "deletado_em")
  private LocalDateTime deletadoEm;

  protected Candidato() { }

  private Candidato(final String nome, final Integer numero, final String legenda, final Cargo cargo) {
    this.nome = nome;
    this.numero = numero;
    this.legenda = legenda;
    this.cargo = cargo;

    final LocalDateTime dataHoraAtual = LocalDateTime.now();

    this.criadoEm = dataHoraAtual;
    this.atualizadoEm = dataHoraAtual;
    this.deletadoEm = null;
  }

  public static Candidato criarCom(final String nome, final Integer numero, final String legenda, final Cargo cargo) {
    return new Candidato(nome, numero, legenda, cargo);
  }

  public UUID getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Integer getNumero() {
    return numero;
  }

  public String getLegenda() {
    return legenda;
  }

  public Cargo getCargo() {
    return cargo;
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

}
