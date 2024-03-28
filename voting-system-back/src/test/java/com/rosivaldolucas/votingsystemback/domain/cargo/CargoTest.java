package com.rosivaldolucas.votingsystemback.domain.cargo;

import com.rosivaldolucas.votingsystemback.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CargoTest {

  @ParameterizedTest
  @ValueSource(strings = { "presidente", "pReSiDeNtE", "PRESIDENTE" })
  public void quandoCriadoNovoCargo_comVariosFormatosDeNomes_entaoNovoCargoCriadoComNomeConvertidoParaMaiusculo(final String nome) {
    final Cargo novoCargo = Cargo.criarCom(nome);

    Assertions.assertNull(novoCargo.getId());
    Assertions.assertEquals(nome.toUpperCase(), novoCargo.getNome());
    Assertions.assertNotNull(novoCargo.getCriadoEm());
    Assertions.assertNotNull(novoCargo.getAtualizadoEm());
    Assertions.assertNull(novoCargo.getDeletadoEm());
  }

  @ParameterizedTest
  @ValueSource(strings = { "", "        ", })
  public void quandoCriadoNovoCargo_comNomeEmBrancoOuComEspacos_entaoDeveLancarException(final String nome) {

    final DomainException domainException = Assertions.assertThrows(DomainException.class, () -> {
      Cargo.criarCom(nome);
    });

    Assertions.assertNotNull(domainException);
    Assertions.assertEquals("nome é obrigatório.", domainException.getMensagem());
  }

  @Test
  public void quandoCriadoNovoCargo_comNomeNulo_entaoDeveLancarException() {

    final DomainException domainException = Assertions.assertThrows(DomainException.class, () -> {
      Cargo.criarCom(null);
    });

    Assertions.assertNotNull(domainException);
    Assertions.assertEquals("nome é obrigatório.", domainException.getMensagem());
  }

  @ParameterizedTest
  @ValueSource(strings = {
          "rosivaldo lucas da silva rosivaldo lucas da silva rosivaldo lucas da silva rosivaldo lucas da silvaaa",
          "rosivaldo lucas da silva rosivaldo lucas da silva rosivaldo lucas da silva rosivaldo lucas da silvaaarosivaldo lucas da silva rosivaldo lucas da silva rosivaldo lucas da silva rosivaldo lucas da silvaaa"
  })
  public void quandoCriadoNovoCargo_comNomeForaDoLimitePermitido_entaoDeveLancarException(final String nome) {

    final DomainException domainException = Assertions.assertThrows(DomainException.class, () -> {
      Cargo.criarCom(nome);
    });

    Assertions.assertNotNull(domainException);
    Assertions.assertEquals("nome deve ter até 100 caracteres.", domainException.getMensagem());
  }

}
