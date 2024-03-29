package com.rosivaldolucas.votingsystemback.api.exceptionhandler;

import com.rosivaldolucas.votingsystemback.api.exceptionhandler.apierror.ApiFieldError;
import com.rosivaldolucas.votingsystemback.domain.exception.EntidadeDuplicadaException;
import com.rosivaldolucas.votingsystemback.domain.exception.EntidadeNaoEncontradaException;
import com.rosivaldolucas.votingsystemback.domain.exception.VotoDuplicadoException;
import com.rosivaldolucas.votingsystemback.domain.exception.VotosComputadosException;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          @NonNull final MethodArgumentNotValidException ex,
          @NonNull final HttpHeaders headers,
          @NonNull final HttpStatusCode status,
          @NonNull final WebRequest request
  ) {
    final List<ApiFieldError> listFieldsErrors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map((error) -> new ApiFieldError(error.getField(), error.getDefaultMessage()))
            .toList();

    final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    problemDetail.setTitle("Erro de validação");
    problemDetail.setDetail("Erro de validação no conteúdo do corpo da requisição enviada");
    problemDetail.setProperty("erros", listFieldsErrors);
    problemDetail.setProperty("timestamp", Instant.now());

    return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
  }

  @ExceptionHandler({ EntidadeNaoEncontradaException.class })
  protected ResponseEntity<Object> handleEntidadeNaoEncontradaException(final EntidadeNaoEncontradaException ex, final WebRequest request) {
    final HttpStatusCode status = HttpStatus.NOT_FOUND;

    final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    problemDetail.setTitle("Entidade não encontrada");
    problemDetail.setDetail("A entidade solicitada não foi encontrada no sistema");
    problemDetail.setProperty("cause", ex.getMensagem());
    problemDetail.setProperty("timestamp", Instant.now());

    return this.handleExceptionInternal(ex, problemDetail, null, status, request);
  }

  @ExceptionHandler({ EntidadeDuplicadaException.class })
  protected ResponseEntity<Object> handleEntidadeDuplicadaException(final EntidadeDuplicadaException ex, final WebRequest request) {
    final HttpStatusCode status = HttpStatus.CONFLICT;

    final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    problemDetail.setTitle("Entidade duplicada");
    problemDetail.setDetail("A entidade solicitada já existe no sistema e não pode ser duplicada");
    problemDetail.setProperty("cause", ex.getMensagem());
    problemDetail.setProperty("timestamp", Instant.now());

    return this.handleExceptionInternal(ex, problemDetail, null, status, request);
  }

  @ExceptionHandler({ VotoDuplicadoException.class })
  protected ResponseEntity<Object> handleVotoDuplicadaException(final VotoDuplicadoException ex, final WebRequest request) {
    final HttpStatusCode status = HttpStatus.CONFLICT;

    final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    problemDetail.setTitle("Duplicidade");
    problemDetail.setDetail("Não é permitido votar duas vezes");
    problemDetail.setProperty("cause", ex.getMensagem());
    problemDetail.setProperty("timestamp", Instant.now());

    return this.handleExceptionInternal(ex, problemDetail, null, status, request);
  }

  @ExceptionHandler({ VotosComputadosException.class })
  protected ResponseEntity<Object> handleVotosComputadosException(final VotosComputadosException ex, final WebRequest request) {
    final HttpStatusCode status = HttpStatus.CONFLICT;

    final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    problemDetail.setTitle("Erro ao deletar recurso");
    problemDetail.setDetail("Há votos associados a este recurso");
    problemDetail.setProperty("cause", ex.getMensagem());
    problemDetail.setProperty("timestamp", Instant.now());

    return this.handleExceptionInternal(ex, problemDetail, null, status, request);
  }

}
