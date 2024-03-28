package com.rosivaldolucas.votingsystemback.api.cargo.dto;

import com.rosivaldolucas.votingsystemback.api.validation.uniquevalue.UniqueValue;
import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;
import jakarta.validation.constraints.NotBlank;

public record NovoCargoInput(
        @NotBlank
        @UniqueValue(domainClass = Cargo.class, fieldName = "nome")
        String nome
) {

}
