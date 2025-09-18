package com.gustavofarias.hexagonalarchiteture.application.port.out;


import com.gustavofarias.hexagonalarchiteture.domain.Livro;

import java.util.List;
import java.util.UUID;

public interface LivroRepositoryPort {
    Livro salvar(Livro livro);
    Livro buscarPorId(UUID id);
    List<Livro> listarTodos();
}
