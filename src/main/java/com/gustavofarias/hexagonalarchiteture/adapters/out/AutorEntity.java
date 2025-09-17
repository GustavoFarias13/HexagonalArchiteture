package com.gustavofarias.hexagonalarchiteture.adapters.out;

import com.gustavofarias.hexagonalarchiteture.domain.Autor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "autores")
public class AutorEntity {

    @Id
    private UUID id;

    private String nome;

    protected AutorEntity() {}

    public AutorEntity(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
    }

    public Autor toDomain() {
        return new Autor(id, nome);
    }

    @Override
    public String toString() {
        return "AutorEntity{id=" + id + ", nome='" + nome + "'}";
    }
}
