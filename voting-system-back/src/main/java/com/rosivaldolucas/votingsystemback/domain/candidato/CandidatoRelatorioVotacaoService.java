package com.rosivaldolucas.votingsystemback.domain.candidato;

import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;
import com.rosivaldolucas.votingsystemback.domain.cargo.CargoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidatoRelatorioVotacaoService {

  private final CandidatoRepository candidatoRepository;
  private final CargoService cargoService;

  public CandidatoRelatorioVotacaoService(final CandidatoRepository candidatoRepository, final CargoService cargoService) {
    this.candidatoRepository = candidatoRepository;
    this.cargoService = cargoService;
  }

  @Cacheable(value = "relatorioCandidatos")
  public List<Candidato> gerar() {
    return this.encontrarCandidatosVencedoresPorCategoriaVesaoJPQL();
  }

  private List<Candidato> encontrarCandidatosVencedoresPorCategoriaVesaoJPQL() {
    return this.candidatoRepository.encontrarCandidatosVencedoresPorCategoria();
  }

  private List<Candidato> encontrarCandidatosVencedoresPorCategoriaVesaoCodigo() {
    final List<Cargo> cargos = this.cargoService.listar();

    final List<Candidato> candidatosVencedores = new ArrayList<>();

    for (final Cargo cargo : cargos) {
      final List<Candidato> cadidatosListadosPorCargo = this.candidatoRepository.findAllByDeletadoEmIsNullAndCargoNome(cargo.getNome());

      Candidato candidatoVencedor = null;
      int quantidadeVotos = Integer.MIN_VALUE;

      if (!cadidatosListadosPorCargo.isEmpty()) {
        for (final Candidato candidato : cadidatosListadosPorCargo) {
          if (candidato.getQuantidadeVotos() > quantidadeVotos) {
            quantidadeVotos = candidato.getQuantidadeVotos();
            candidatoVencedor = candidato;
          }
        }

        if (candidatoVencedor != null) {
          candidatosVencedores.add(candidatoVencedor);
        }
      }
    }

    return candidatosVencedores;
  }

}
