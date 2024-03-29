package com.rosivaldolucas.votingsystemback.api.eleitor.dto;

import com.rosivaldolucas.votingsystemback.api.validation.uniquevalue.UniqueValue;
import com.rosivaldolucas.votingsystemback.domain.eleitor.Eleitor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record NovoEleitorInput(
        @NotBlank
        @Size(max = 100)
        String nome,

        @NotBlank
        @CPF
        @UniqueValue(domainClass = Eleitor.class, fieldName = "cpf")
        String cpf
) {
}
