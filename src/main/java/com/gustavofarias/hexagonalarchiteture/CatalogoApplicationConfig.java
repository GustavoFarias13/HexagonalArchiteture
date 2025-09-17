package com.gustavofarias.hexagonalarchiteture;

import com.gustavofarias.hexagonalarchiteture.application.port.out.LivroRepositoryPort;
import com.gustavofarias.hexagonalarchiteture.application.service.LivroService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogoApplicationConfig {

    @Bean
    public LivroService livroService(LivroRepositoryPort repositoryPort) {
        return new LivroService(repositoryPort);
    }
}
