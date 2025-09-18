package com.gustavofarias.hexagonalarchiteture.application.service;

import com.gustavofarias.hexagonalarchiteture.application.port.out.EmailPort;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import org.springframework.stereotype.Service;

@Service
public class FakeEmailService implements EmailPort {

    @Override
    public void enviarEmailCadastroLivro(Livro livro) {
        System.out.println("📩 [FAKE EMAIL] Para: destino@livraria.com");
        System.out.println("Assunto: Novo livro cadastrado - " + livro.getTitulo());
        System.out.println("Corpo: O livro \"" + livro.getTitulo() + "\" de " +
                livro.getAutor().getNome() + " (" + livro.getAnoPublicacao() + ") foi cadastrado com sucesso!");
    }
}
