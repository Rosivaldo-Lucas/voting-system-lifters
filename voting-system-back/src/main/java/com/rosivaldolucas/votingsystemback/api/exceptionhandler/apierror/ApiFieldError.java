package com.rosivaldolucas.votingsystemback.api.exceptionhandler.apierror;

public record ApiFieldError(
        String campo,
        String mensagem
) {
}
