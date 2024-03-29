package com.rosivaldolucas.votingsystemback.api.eleitor.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UUID;

public record VotoEleitorInput(
        @NotBlank
        @UUID
        String idCandidato,

        @NotBlank
        @UUID
        String idEleitor
) {
}
