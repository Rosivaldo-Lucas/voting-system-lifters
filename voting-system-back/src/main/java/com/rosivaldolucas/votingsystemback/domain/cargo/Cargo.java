package com.rosivaldolucas.votingsystemback.domain.cargo;

import com.rosivaldolucas.votingsystemback.domain.entity.BaseEntity;
import com.rosivaldolucas.votingsystemback.domain.exception.DomainException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Entity
@Table(name = "cargos")
public class Cargo extends BaseEntity implements Serializable {

  @Column(name = "nome")
  private String nome;

  protected Cargo() { }

  private Cargo(final String nome) {
    super();
    this.nome = nome != null ? this.converterNomeParaMaiusculo(nome) : null;

    this.validar();
  }

  public static Cargo criarCom(final String nome) {
    return new Cargo(nome);
  }

  public void atualizar(final String novoNome) {
    final String novoNomeFormatado = converterNomeParaMaiusculo(novoNome);

    if (!this.nome.equals(novoNomeFormatado)) {
      this.nome = novoNomeFormatado;

      this.validar();

      this.atualizadoEm();
    }
  }

  public void deletar() {
    this.deletadoEm();
  }

  public void validar() {
    if (!StringUtils.hasText(this.nome)) throw new DomainException("nome é obrigatório.");
    if (this.nome.length() < 5 || this.nome.length() > 100) throw new DomainException("nome deve ter até 100 caracteres.");
  }

  private String converterNomeParaMaiusculo(final String nome) {
    return nome.toUpperCase();
  }

  public String getNome() {
    return nome;
  }

}
