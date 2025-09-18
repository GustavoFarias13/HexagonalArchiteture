package com.gustavofarias.hexagonalarchiteture.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Livro {

    @Id
    private UUID id;
    private String titulo;
    private int anoPublicacao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Livro() {
        this.id = UUID.randomUUID();
    }

    public Livro(String titulo, Autor autor, int anoPublicacao) {
        this();
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public Autor getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
}