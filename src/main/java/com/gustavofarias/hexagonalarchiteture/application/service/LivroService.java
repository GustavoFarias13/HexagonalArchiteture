package com.gustavofarias.hexagonalarchiteture.application.service;

import com.gustavofarias.hexagonalarchiteture.application.port.in.BuscarLivroPorIdUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.CadastrarLivroUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.ListarLivrosUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.out.LivroRepositoryPort;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;

import java.util.List;
import java.util.UUID;

public class LivroService implements
        CadastrarLivroUseCase, BuscarLivroPorIdUseCase, ListarLivrosUseCase {

    private final LivroRepositoryPort repository;

    public LivroService(LivroRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Livro cadastrar(Livro livro) {
        return repository.salvar(livro);
    }

    @Override
    public Livro buscarPorId(UUID id) {
        return repository.buscarPorId(id);
    }

    @Override
    public List<Livro> listarTodos() {
        return repository.listarTodos();
    }
}
