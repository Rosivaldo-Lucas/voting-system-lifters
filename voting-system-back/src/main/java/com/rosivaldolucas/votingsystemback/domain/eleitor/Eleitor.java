package com.rosivaldolucas.votingsystemback.domain.eleitor;

import com.rosivaldolucas.votingsystemback.domain.entity.BaseEntity;
import com.rosivaldolucas.votingsystemback.domain.exception.DomainException;
import com.rosivaldolucas.votingsystemback.domain.exception.VotosComputadosException;
import com.rosivaldolucas.votingsystemback.domain.voto.Voto;
import jakarta.persistence.*;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "eleitores")
public class Eleitor extends BaseEntity {

  @Column(name = "nome")
  private String nome;

  @Column(name = "cpf")
  private String cpf;

  @OneToMany(mappedBy = "eleitor")
  private final Set<Voto> votos = new HashSet<>();

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

  public void atualizar(final String nome, final String cpf) {
    this.nome = nome;
    this.cpf = cpf;

    this.validar();
    this.atualizadoEm();
  }

  public void deletar() {
    final boolean isTemVotosComputados = !this.votos.isEmpty();

    if (isTemVotosComputados) {
      throw new VotosComputadosException("Não é possível deletar o Eleitor, pois existem votos computados.");
    }

    this.deletadoEm();
  }

  private void validar() {
    if (!StringUtils.hasText(this.nome)) throw new DomainException("nome é obrigatório.");
    if (this.nome.length() < 5 || this.nome.length() > 100) throw new DomainException("nome deve ter até 100 caracteres.");

    if (!StringUtils.hasText(this.cpf)) throw new DomainException("cpf é obrigatório.");
    if (this.cpf.length() != 11) throw new DomainException("cpf deve ter 11 caracteres.");
  }

}
