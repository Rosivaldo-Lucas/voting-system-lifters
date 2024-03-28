package com.rosivaldolucas.votingsystemback.api.cargo.dto;

import jakarta.validation.constraints.NotBlank;

public record AtualizarCargoInput(
        @NotBlank
        String nome
) {
}
