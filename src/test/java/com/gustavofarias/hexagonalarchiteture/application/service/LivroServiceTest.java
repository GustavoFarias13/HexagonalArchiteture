package com.gustavofarias.hexagonalarchiteture.application.service;

import com.gustavofarias.hexagonalarchiteture.application.port.out.EmailPort;
import com.gustavofarias.hexagonalarchiteture.application.port.out.LivroRepositoryPort;
import com.gustavofarias.hexagonalarchiteture.domain.Autor;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivroServiceTest {

    private LivroRepositoryPort repositoryPort;
    private EmailPort emailPort;
    private LivroService livroService;

    @BeforeEach
    void setup() {
        repositoryPort = mock(LivroRepositoryPort.class);
        emailPort = new FakeEmailService(); // usa o email fake
        livroService = new LivroService(repositoryPort, emailPort);
    }

    @Test
    void deveCadastrarLivroEEnviarEmail() {
        Autor autor = new Autor("Machado de Assis");
        Livro livro = new Livro("Dom Casmurro", autor, 1899);

        when(repositoryPort.salvar(livro)).thenReturn(livro);

        Livro salvo = livroService.cadastrarLivro(livro);

        assertEquals("Dom Casmurro", salvo.getTitulo());
        verify(repositoryPort, times(1)).salvar(livro);

        System.out.println("Livro cadastrado: " + salvo.getTitulo() +
                " (" + salvo.getAnoPublicacao() + ") - Autor: " + salvo.getAutor().getNome());
    }

    @Test
    void deveBuscarLivroPorId() {
        UUID id = UUID.randomUUID();
        Livro livro = new Livro("Memórias Póstumas", new Autor("Machado de Assis"), 1881);

        when(repositoryPort.buscarPorId(id)).thenReturn(livro);

        Livro encontrado = livroService.buscarPorId(id);

        assertEquals("Memórias Póstumas", encontrado.getTitulo());
        verify(repositoryPort).buscarPorId(id);

        System.out.println("Livro encontrado: " + encontrado.getTitulo() +
                " (" + encontrado.getAnoPublicacao() + ") - Autor: " + encontrado.getAutor().getNome());
    }

    @Test
    void deveListarTodosOsLivros() {
        List<Livro> livros = List.of(
                new Livro("Livro 1", new Autor("Autor 1"), 2001),
                new Livro("Livro 2", new Autor("Autor 2"), 2002)
        );

        when(repositoryPort.listarTodos()).thenReturn(livros);

        List<Livro> resultado = livroService.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Livro 1", resultado.get(0).getTitulo());

        System.out.println("Lista de livros retornada:");
        resultado.forEach(l -> System.out.println(" - " + l.getTitulo() + " (" + l.getAnoPublicacao() + ") - Autor: " + l.getAutor().getNome()));
    }

    @Test
    void deveEnviarEmailQuandoCadastrarLivro() {
        Autor autor = new Autor("Clarice Lispector");
        Livro livro = new Livro("A Hora da Estrela", autor, 1977);

        when(repositoryPort.salvar(livro)).thenReturn(livro);

        livroService.cadastrarLivro(livro);

        System.out.println("Email enviado para cadastro do livro: " + livro.getTitulo());
    }
}
