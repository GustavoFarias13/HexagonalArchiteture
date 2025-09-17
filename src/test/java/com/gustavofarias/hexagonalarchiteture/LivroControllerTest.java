package com.gustavofarias.hexagonalarchiteture;

import com.gustavofarias.hexagonalarchiteture.adapters.in.LivroDTO;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LivroControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void deveCadastrarLivro() {
        LivroDTO dto = new LivroDTO("O Hobbit", "Tolkien", 1937);

        ResponseEntity<Livro> response = restTemplate.postForEntity("/livros", dto, Livro.class);

        System.out.println("📘 [Cadastrar Livro] Status: " + response.getStatusCode());
        System.out.println("📘 [Cadastrar Livro] Retorno: " + response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitulo()).isEqualTo("O Hobbit");
    }

    @Test
    void deveBuscarLivroPorId() {
        LivroDTO dto = new LivroDTO("Duna", "Frank Herbert", 1965);
        Livro livroCriado = restTemplate.postForEntity("/livros", dto, Livro.class).getBody();

        Assertions.assertNotNull(livroCriado);
        ResponseEntity<Livro> response =
                restTemplate.getForEntity("/livros/" + livroCriado.getId(), Livro.class);

        System.out.println("🔎 [Buscar Livro] ID enviado: " + livroCriado.getId());
        System.out.println("🔎 [Buscar Livro] Status: " + response.getStatusCode());
        System.out.println("🔎 [Buscar Livro] Retorno: " + response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitulo()).isEqualTo("Duna");
    }

    @Test
    void deveListarLivros() {
        restTemplate.postForEntity("/livros", new LivroDTO("Neuromancer", "William Gibson", 1984), Livro.class);
        restTemplate.postForEntity("/livros", new LivroDTO("Fundação", "Isaac Asimov", 1951), Livro.class);

        ResponseEntity<Livro[]> response = restTemplate.getForEntity("/livros", Livro[].class);

        System.out.println("📚 [Listar Livros] Status: " + response.getStatusCode());
        if (response.getBody() != null) {
            System.out.println("📚 [Listar Livros] Quantidade: " + response.getBody().length);
            for (Livro livro : response.getBody()) {
                System.out.println(" - " + livro.getTitulo() + " (" + livro.getAutor().getNome() + ")");
            }
        }

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThanOrEqualTo(2);
    }
}