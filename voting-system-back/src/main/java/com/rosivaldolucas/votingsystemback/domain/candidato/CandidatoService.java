package com.rosivaldolucas.votingsystemback.domain.candidato;

import com.rosivaldolucas.votingsystemback.api.candidato.dto.NovoCandidatoInput;
import com.rosivaldolucas.votingsystemback.domain.candidato.exception.CandidatoNaoEncontradoException;
import com.rosivaldolucas.votingsystemback.domain.cargo.Cargo;
import com.rosivaldolucas.votingsystemback.domain.cargo.CargoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {

  private final CandidatoRepository candidatoRepository;
  private final CargoService cargoService;

  public CandidatoService(final CandidatoRepository candidatoRepository, final CargoService cargoService) {
    this.candidatoRepository = candidatoRepository;
    this.cargoService = cargoService;
  }

  public Page<Candidato> listar(int numeroPagina, int tamanhoPagina) {
    return this.candidatoRepository.findAllByDeletadoEmIsNull(PageRequest.of(numeroPagina, tamanhoPagina));
  }

  public Candidato buscarPorId(final String idCandidato) {
    return this.candidatoRepository
            .findById(idCandidato)
            .orElseThrow(() -> new CandidatoNaoEncontradoException(String.format("Candidato de 'id' %s não encontrado.", idCandidato)));
  }

  public Candidato cadastrar(final NovoCandidatoInput input) {
    final Cargo cargoCanditado = this.cargoService.buscarPorId(input.idCargo());

    final Candidato candidatoASerCadastrado = Candidato.criarCom(input.nome(), input.numero(), input.legenda(), cargoCanditado);

    return this.candidatoRepository.save(candidatoASerCadastrado);
  }

}
