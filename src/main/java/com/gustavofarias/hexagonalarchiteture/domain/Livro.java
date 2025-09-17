package com.gustavofarias.hexagonalarchiteture.domain;

import java.util.UUID;

public class Livro {
    private UUID id;
    private String titulo;
    private Autor autor;
    private int anoPublicacao;

    public Livro(UUID id, String titulo, Autor autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public Autor getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }

    @Override
    public String toString() {
        return "Livro{id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", anoPublicacao=" + anoPublicacao +
                '}';
    }
}