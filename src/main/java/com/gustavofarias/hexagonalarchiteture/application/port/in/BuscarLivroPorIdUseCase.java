package com.gustavofarias.hexagonalarchiteture.application.port.in;

import com.gustavofarias.hexagonalarchiteture.domain.Livro;

import java.util.UUID;

public interface BuscarLivroPorIdUseCase {
    Livro buscarPorId(UUID id);
}
