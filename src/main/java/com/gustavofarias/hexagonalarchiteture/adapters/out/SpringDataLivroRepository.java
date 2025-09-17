package com.gustavofarias.hexagonalarchiteture.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringDataLivroRepository extends JpaRepository<LivroEntity, UUID> {}
