package com.gustavofarias.hexagonalarchiteture.adapters.out;

import com.gustavofarias.hexagonalarchiteture.application.port.out.LivroRepositoryPort;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LivroRepositoryAdapter implements LivroRepositoryPort {

    private final SpringDataLivroRepository repository;

    public LivroRepositoryAdapter(SpringDataLivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public Livro salvar(Livro livro) {
        return repository.save(new LivroEntity(livro)).toDomain();
    }

    @Override
    public Livro buscarPorId(UUID id) {
        return repository.findById(id).map(LivroEntity::toDomain).orElse(null);
    }

    @Override
    public List<Livro> listarTodos() {
        return repository.findAll().stream().map(LivroEntity::toDomain).toList();
    }
}
