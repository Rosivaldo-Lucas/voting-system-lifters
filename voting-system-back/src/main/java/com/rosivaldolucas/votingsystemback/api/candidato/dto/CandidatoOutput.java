package com.rosivaldolucas.votingsystemback.api.candidato.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rosivaldolucas.votingsystemback.api.cargo.dto.CargoOutput;
import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CandidatoOutput(
        String id,

        String nome,

        Integer numero,

        String legenda,

        @JsonIgnoreProperties({ "criadoEm", "atualizadoEm", "deletadoEm" })
        CargoOutput cargo,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime criadoEm,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime atualizadoEm,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime deletadoEm
) {

  public static CandidatoOutput criar(final Candidato candidato) {
    final CargoOutput cargoOutput = CargoOutput.criar(candidato.getCargo());

    return new CandidatoOutput(
            candidato.getId().toString(),
            candidato.getNome(),
            candidato.getNumero(),
            candidato.getLegenda(),
            cargoOutput,
            candidato.getCriadoEm(),
            candidato.getAtualizadoEm(),
            candidato.getDeletadoEm()
    );
  }

  public static Page<CandidatoOutput> criar(final Page<Candidato> candidatosPage) {
    final List<CandidatoOutput> candidatosOutput = candidatosPage.getContent().stream().map((CandidatoOutput::criar)).toList();

    return new PageImpl<>(candidatosOutput, candidatosPage.getPageable(), candidatosPage.getTotalElements());
  }

}
