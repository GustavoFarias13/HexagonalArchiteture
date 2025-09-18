package com.gustavofarias.hexagonalarchiteture;

import com.gustavofarias.hexagonalarchiteture.domain.Autor;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import com.gustavofarias.hexagonalarchiteture.infra.adapter.out.JpaLivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class HexagonalArchitetureApplicationTests {

    @Autowired
    private JpaLivroRepository jpaRepo;

    @BeforeEach
    void limparBase() {
        jpaRepo.deleteAll(); // garante que cada teste começa limpo
    }

    @Test
    void devePersistirLivroNoBanco() {
        Autor autor = new Autor("J. K. Rowling");
        Livro livro = new Livro("Harry Potter", autor, 1997);

        // Persistindo
        jpaRepo.save(livro);

        // Recuperando
        List<Livro> livros = jpaRepo.findAll();

        // Assertivas detalhadas
        assertFalse(livros.isEmpty(), "A lista de livros não deve estar vazia");
        assertEquals(1, livros.size(), "Deve existir apenas 1 livro no banco");
        Livro l = livros.get(0);
        assertEquals("Harry Potter", l.getTitulo());
        assertEquals("J. K. Rowling", l.getAutor().getNome());
        assertEquals(1997, l.getAnoPublicacao());

        // Impressão no terminal
        System.out.println(" Livro persistido:");
        System.out.println(" - Título: " + l.getTitulo());
        System.out.println(" - Autor: " + l.getAutor().getNome());
        System.out.println(" - Ano: " + l.getAnoPublicacao());
    }
}
