package com.rosivaldolucas.votingsystemback.api.cargo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CargoOutput(
        String id,
        String nome,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime criadoEm,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime atualizadoEm,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime deletadoEm
) {

  public CargoOutput(final Cargo cargo) {
    this(cargo.getId().toString(), cargo.getNome(), cargo.getCriadoEm(), cargo.getAtualizadoEm(), cargo.getDeletadoEm());
  }

}
