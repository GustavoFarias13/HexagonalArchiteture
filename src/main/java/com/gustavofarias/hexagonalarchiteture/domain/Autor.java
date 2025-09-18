package com.gustavofarias.hexagonalarchiteture.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Autor {

    @Id
    private UUID id;
    private String nome;

    public Autor() {
        this.id = UUID.randomUUID();
    }

    public Autor(String nome) {
        this();
        this.nome = nome;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
}
