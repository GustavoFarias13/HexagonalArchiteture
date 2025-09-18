package com.gustavofarias.hexagonalarchiteture.infra.adapter.out;

import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JpaLivroRepository extends JpaRepository<Livro, UUID> {}
