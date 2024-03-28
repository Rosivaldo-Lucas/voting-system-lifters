package com.rosivaldolucas.votingsystemback.api.candidato.dto;

import com.rosivaldolucas.votingsystemback.domain.candidato.Candidato;

import java.util.List;

public record CandidatoVencedorerOutput(
        String idCargo,

        String nomeCargo,

        Integer votos,

        String idCandidatoVencedor,

        String nomeCandidatoVencedor
) {

  public static CandidatoVencedorerOutput criar(final Candidato candidatoVencedor) {
    return new CandidatoVencedorerOutput(
            candidatoVencedor.getCargo().getId().toString(),
            candidatoVencedor.getCargo().getNome(),
            candidatoVencedor.getQuantidadeVotos(),
            candidatoVencedor.getId().toString(),
            candidatoVencedor.getNome()
    );
  }

  public static List<CandidatoVencedorerOutput> criar(final List<Candidato> candidatosVencedores) {
    return candidatosVencedores.stream().map(CandidatoVencedorerOutput::criar).toList();
  }

}
