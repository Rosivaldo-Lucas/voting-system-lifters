package com.rosivaldolucas.votingsystemback.api.candidato.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

public record AtualizarCandidatoInput(
        @NotBlank
        @Size(max = 100)
        String nome,

        @NotNull
        Integer numero,

        @NotBlank
        @Size(max = 255)
        String legenda,

        @NotBlank
        @UUID
        String idCargo
) {
}
