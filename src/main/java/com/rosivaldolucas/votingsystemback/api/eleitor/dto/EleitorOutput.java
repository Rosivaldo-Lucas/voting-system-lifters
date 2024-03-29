package com.rosivaldolucas.votingsystemback.api.eleitor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rosivaldolucas.votingsystemback.domain.eleitor.Eleitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EleitorOutput(
        String id,

        String nome,

        String cpf,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime criadoEm,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime atualizadoEm,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime deletadoEm
) {

  public static EleitorOutput criar(final Eleitor eleitor) {
    return new EleitorOutput(eleitor.getId().toString(), eleitor.getNome(), eleitor.getCpf(), eleitor.getCriadoEm(), eleitor.getAtualizadoEm(), eleitor.getDeletadoEm());
  }

  public static Page<EleitorOutput> criar(final Page<Eleitor> eleitoresPage) {
    final List<EleitorOutput> eleitoresOutput = eleitoresPage.getContent().stream().map((EleitorOutput::criar)).toList();

    return new PageImpl<>(eleitoresOutput, eleitoresPage.getPageable(), eleitoresPage.getTotalElements());
  }

}
