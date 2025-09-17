package com.gustavofarias.hexagonalarchiteture.application.port.in;

import com.gustavofarias.hexagonalarchiteture.domain.Livro;

public interface CadastrarLivroUseCase {
    Livro cadastrar(Livro livro);
}
