package com.rosivaldolucas.votingsystemback.domain.candidato;

import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;
import com.rosivaldolucas.votingsystemback.domain.entity.BaseEntity;
import com.rosivaldolucas.votingsystemback.domain.exception.DomainException;
import com.rosivaldolucas.votingsystemback.domain.voto.Voto;
import jakarta.persistence.*;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "candidatos")
public class Candidato extends BaseEntity {

  @Column(name = "nome")
  private String nome;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "legenda")
  private String legenda;

  @ManyToOne
  @JoinColumn(name = "id_cargo")
  private Cargo cargo;

  @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL)
  private final Set<Voto> votos = new HashSet<>();

  protected Candidato() { }

  private Candidato(final String nome, final Integer numero, final String legenda, final Cargo cargo) {
    super();

    this.nome = nome;
    this.numero = numero;
    this.legenda = legenda;
    this.cargo = cargo;

    this.validar();
  }

  public static Candidato criarCom(final String nome, final Integer numero, final String legenda, final Cargo cargo) {
    return new Candidato(nome, numero, legenda, cargo);
  }

  public void receberVoto(final Voto voto) {
    this.votos.add(voto);
  }

  public void atualizar(final String nome, final Integer numero, final String legenda, final Cargo cargo) {
    this.nome = nome;
    this.numero = numero;
    this.legenda = legenda;
    this.cargo = cargo;

    this.validar();

    this.atualizadoEm();
  }

  public void deletar() {
    this.deletadoEm();
  }

  private void validar() {
    if (!StringUtils.hasText(this.nome)) throw new DomainException("nome é obrigatório.");
    if (this.nome.length() < 5 || this.nome.length() > 100) throw new DomainException("nome deve ter até 100 caracteres.");

    if (this.numero == null) throw new DomainException("número é obrigatório.");
    if (this.legenda.length() > 255) throw new DomainException("legenda deve ter 255 caracteres.");
    if (this.cargo == null) throw new DomainException("cargo é obrigatório.");
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

  public Set<Voto> getVotos() {
    return votos;
  }

}
