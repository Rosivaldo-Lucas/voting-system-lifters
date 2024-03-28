package com.rosivaldolucas.votingsystemback.api.eleitor.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record AtualizarEleitorInput(
        @NotBlank
        String nome,

        @NotBlank
        @CPF
        String cpf
) {
}
