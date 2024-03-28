package com.rosivaldolucas.votingsystemback.domain.eleitor;

import com.rosivaldolucas.votingsystemback.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "eleitores")
public class Eleitor extends BaseEntity {

  @Column(name = "nome")
  private String nome;

  @Column(name = "cpf")
  private String cpf;

  protected Eleitor() { }

  private Eleitor(final String nome, final String cpf) {
    super();
    this.nome = nome;
    this.cpf = cpf;
  }

  public static Eleitor criarCom(final String nome, final String cpf) {
    return new Eleitor(nome, cpf);
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

}
