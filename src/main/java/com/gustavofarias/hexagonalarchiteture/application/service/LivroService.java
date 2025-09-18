package com.gustavofarias.hexagonalarchiteture.application.service;

import com.gustavofarias.hexagonalarchiteture.application.port.in.BuscarLivroPorIdUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.CadastrarLivroUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.ListarLivrosUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.out.EmailPort;
import com.gustavofarias.hexagonalarchiteture.application.port.out.LivroRepositoryPort;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;

import java.util.List;
import java.util.UUID;

public class LivroService implements
        CadastrarLivroUseCase,
        BuscarLivroPorIdUseCase,
        ListarLivrosUseCase {

    private final LivroRepositoryPort repositoryPort;
    private final EmailPort emailPort;

    public LivroService(LivroRepositoryPort repositoryPort, EmailPort emailPort) {
        this.repositoryPort = repositoryPort;
        this.emailPort = emailPort;
    }

    @Override
    public Livro cadastrarLivro(Livro livro) {
        Livro salvo = repositoryPort.salvar(livro);
        emailPort.enviarEmailCadastroLivro(salvo);
        return salvo;
    }

    @Override
    public Livro buscarPorId(UUID id) {
        return repositoryPort.buscarPorId(id);
    }

    @Override
    public List<Livro> listarTodos() {
        return repositoryPort.listarTodos();
    }
}
