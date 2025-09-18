package com.gustavofarias.hexagonalarchiteture.infra.adapter.out;

import com.gustavofarias.hexagonalarchiteture.application.port.out.EmailPort;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailAdapter implements EmailPort {

    private final JavaMailSender mailSender;

    public EmailAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarEmailCadastroLivro(Livro livro) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("destinatario@exemplo.com");
        message.setSubject("Novo livro cadastrado!");
        message.setText("Livro: " + livro.getTitulo() +
                "\nAutor: " + livro.getAutor().getNome() +
                "\nAno: " + livro.getAnoPublicacao());
        mailSender.send(message);
    }
}
