package com.gustavofarias.hexagonalarchiteture.infra.adapter.out;

import com.gustavofarias.hexagonalarchiteture.application.port.out.LivroRepositoryPort;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;

import java.util.List;
import java.util.UUID;

public class LivroRepositoryAdapter implements LivroRepositoryPort {

    private final JpaLivroRepository jpaRepository;

    public LivroRepositoryAdapter(JpaLivroRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Livro salvar(Livro livro) {
        return jpaRepository.save(livro);
    }

    @Override
    public Livro buscarPorId(UUID id) {
        return jpaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Livro> listarTodos() {
        return jpaRepository.findAll();
    }
}
