package com.gustavofarias.hexagonalarchiteture.application.port.out;

import com.gustavofarias.hexagonalarchiteture.domain.Livro;

public interface EmailPort {
    void enviarEmailCadastroLivro(Livro livro);
}
