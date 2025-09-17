package com.gustavofarias.hexagonalarchiteture.domain;

import java.util.UUID;

public class Autor {
    private UUID id;
    private String nome;

    public Autor(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return nome + " (" + id + ")";
    }
}
