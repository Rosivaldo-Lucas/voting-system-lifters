package com.rosivaldolucas.votingsystemback.api.cargo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;

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

  public static CargoOutput criar(final Cargo cargo) {
    return new CargoOutput(cargo.getId().toString(), cargo.getNome(), cargo.getCriadoEm(), cargo.getAtualizadoEm(), cargo.getDeletadoEm());
  }

  public static Page<CargoOutput> criar(final Page<Cargo> cargosPage) {
    final List<CargoOutput> cargosOutput = cargosPage.getContent().stream().map((CargoOutput::criar)).toList();

    return new PageImpl<>(cargosOutput, cargosPage.getPageable(), cargosPage.getTotalElements());
  }

}
