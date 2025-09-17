package com.gustavofarias.hexagonalarchiteture.adapters.out;

import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "livros")
public class LivroEntity {

    @Id
    private UUID id;
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL) // salva Autor junto com Livro
    private AutorEntity autor;

    private int anoPublicacao;

    protected LivroEntity() {}

    public LivroEntity(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = new AutorEntity(livro.getAutor());
        this.anoPublicacao = livro.getAnoPublicacao();
    }

    public Livro toDomain() {
        return new Livro(id, titulo, autor.toDomain(), anoPublicacao);
    }
}
