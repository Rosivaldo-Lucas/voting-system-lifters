package com.rosivaldolucas.votingsystemback.api.candidato.dto;

import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;

import java.io.Serializable;
import java.util.List;

public record CandidatoVencedorOutput(
        String idCargo,

        String nomeCargo,

        Integer votos,

        String idCandidatoVencedor,

        String nomeCandidatoVencedor
) implements Serializable {

  public static CandidatoVencedorOutput criar(final Candidato candidatoVencedor) {
    return new CandidatoVencedorOutput(
            candidatoVencedor.getCargo().getId(),
            candidatoVencedor.getCargo().getNome(),
            candidatoVencedor.getQuantidadeVotos(),
            candidatoVencedor.getId(),
            candidatoVencedor.getNome()
    );
  }

  public static List<CandidatoVencedorOutput> criar(final List<Candidato> candidatosVencedores) {
    return candidatosVencedores.stream().map(CandidatoVencedorOutput::criar).toList();
  }

}
