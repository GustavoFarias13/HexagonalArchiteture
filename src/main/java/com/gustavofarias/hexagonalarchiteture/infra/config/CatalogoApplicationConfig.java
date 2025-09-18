package com.gustavofarias.hexagonalarchiteture.infra.config;

import com.gustavofarias.hexagonalarchiteture.application.port.out.EmailPort;
import com.gustavofarias.hexagonalarchiteture.application.port.out.LivroRepositoryPort;
import com.gustavofarias.hexagonalarchiteture.application.service.LivroService;
import com.gustavofarias.hexagonalarchiteture.infra.adapter.out.EmailAdapter;
import com.gustavofarias.hexagonalarchiteture.infra.adapter.out.JpaLivroRepository;
import com.gustavofarias.hexagonalarchiteture.infra.adapter.out.LivroRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class CatalogoApplicationConfig {

    @Bean
    LivroRepositoryPort livroRepositoryPort(JpaLivroRepository jpaRepo) {
        return new LivroRepositoryAdapter(jpaRepo);
    }

    @Bean
    EmailPort emailPort(JavaMailSender mailSender) {
        return new EmailAdapter(mailSender);
    }

    @Bean
    LivroService livroService(LivroRepositoryPort repo, EmailPort emailPort) {
        return new LivroService(repo, emailPort);
    }
}
