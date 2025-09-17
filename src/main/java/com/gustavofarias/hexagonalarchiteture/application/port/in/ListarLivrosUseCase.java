package com.gustavofarias.hexagonalarchiteture.application.port.in;

import com.gustavofarias.hexagonalarchiteture.domain.Livro;

import java.util.List;

public interface ListarLivrosUseCase {
    List<Livro> listarTodos();
}
