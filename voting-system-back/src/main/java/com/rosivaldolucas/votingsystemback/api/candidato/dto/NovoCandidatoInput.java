package com.rosivaldolucas.votingsystemback.api.candidato.dto;

import com.rosivaldolucas.votingsystemback.api.validation.uniquevalue.UniqueValue;
import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UUID;

public record NovoCandidatoInput(
        @NotBlank
        @Size(max = 100)
        String nome,

        @NotNull
        @UniqueValue(domainClass = Candidato.class, fieldName = "numero")
        Integer numero,

        @NotBlank
        @Size(max = 255)
        String legenda,

        @NotBlank
        @UUID
        String idCargo
) {
}
