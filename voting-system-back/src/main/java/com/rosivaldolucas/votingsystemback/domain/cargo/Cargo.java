package com.rosivaldolucas.votingsystemback.domain.cargo;

import com.rosivaldolucas.votingsystemback.domain.exception.DomainException;
import jakarta.persistence.*;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cargos")
public class Cargo {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "criado_em")
  private LocalDateTime criadoEm;

  @Column(name = "atualizado_em")
  private LocalDateTime atualizadoEm;

  @Column(name = "deletado_em")
  private LocalDateTime deletadoEm;

  protected Cargo() { }

  private Cargo(final UUID id, final String nome) {
    final LocalDateTime dataHoraAtual = LocalDateTime.now();

    this.id = id;
    this.nome = nome != null ? this.converterNomeParaMaiusculo(nome) : null;
    this.criadoEm = dataHoraAtual;
    this.atualizadoEm = dataHoraAtual;
    this.deletadoEm = null;

    this.validar();
  }

  public static Cargo criarCom(final String nome) {
    return new Cargo(null, nome);
  }

  public void atualizar(final String novoNome) {
    final String novoNomeFormatado = converterNomeParaMaiusculo(novoNome);

    if (!this.nome.equals(novoNomeFormatado)) {
      this.nome = novoNomeFormatado;

      this.validar();

      this.atualizadoEm = LocalDateTime.now();
    }
  }

  public void validar() {
    if (!StringUtils.hasText(this.nome)) throw new DomainException("nome é obrigatório.");
    if (this.nome.length() < 5 || this.nome.length() > 100) throw new DomainException("nome deve ter até 100 caracteres.");
  }

  private String converterNomeParaMaiusculo(final String nome) {
    return nome.toUpperCase();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDateTime getCriadoEm() {
    return criadoEm;
  }

  public void setCriadoEm(LocalDateTime criadoEm) {
    this.criadoEm = criadoEm;
  }

  public LocalDateTime getAtualizadoEm() {
    return atualizadoEm;
  }

  public void setAtualizadoEm(LocalDateTime atualizadoEm) {
    this.atualizadoEm = atualizadoEm;
  }

  public LocalDateTime getDeletadoEm() {
    return deletadoEm;
  }

  public void setDeletadoEm(LocalDateTime deletadoEm) {
    this.deletadoEm = deletadoEm;
  }

}
