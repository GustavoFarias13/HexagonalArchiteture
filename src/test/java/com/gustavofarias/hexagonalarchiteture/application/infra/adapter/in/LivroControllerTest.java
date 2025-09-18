package com.gustavofarias.hexagonalarchiteture.application.infra.adapter.in;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavofarias.hexagonalarchiteture.application.port.in.BuscarLivroPorIdUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.CadastrarLivroUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.ListarLivrosUseCase;
import com.gustavofarias.hexagonalarchiteture.domain.Autor;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import com.gustavofarias.hexagonalarchiteture.infra.adapter.in.LivroController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


class LivroControllerTest {

    private MockMvc mockMvc;
    private CadastrarLivroUseCase cadastrarUseCase;
    private BuscarLivroPorIdUseCase buscarUseCase;
    private ListarLivrosUseCase listarUseCase;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        cadastrarUseCase = Mockito.mock(CadastrarLivroUseCase.class);
        buscarUseCase = Mockito.mock(BuscarLivroPorIdUseCase.class);
        listarUseCase = Mockito.mock(ListarLivrosUseCase.class);

        LivroController controller = new LivroController(cadastrarUseCase, buscarUseCase, listarUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void deveCadastrarLivro() throws Exception {
        Livro livro = new Livro("Novo Livro", new Autor("Novo Autor"), 2024);
        when(cadastrarUseCase.cadastrarLivro(Mockito.any())).thenReturn(livro);

        String json = objectMapper.writeValueAsString(livro);

        MvcResult result = mockMvc.perform(post("/livros")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Novo Livro"))
                .andExpect(jsonPath("$.autor.nome").value("Novo Autor"))
                .andReturn(); // <- pega a resposta

        // transforma o JSON da resposta de volta em Livro
        String responseBody = result.getResponse().getContentAsString();
        Livro responseLivro = objectMapper.readValue(responseBody, Livro.class);

        // mostra no terminal
        System.out.println("Livro retornado: " + responseLivro.getTitulo() +
                " (" + responseLivro.getAnoPublicacao() +
                ") - Autor: " + responseLivro.getAutor().getNome());
    }


    @Test
    void deveBuscarLivroPorId() throws Exception {
        UUID id = UUID.randomUUID();
        Livro livro = new Livro("Livro Unico", new Autor("Autor Unico"), 2022);
        when(buscarUseCase.buscarPorId(id)).thenReturn(livro);

        MvcResult result = mockMvc.perform(get("/livros/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Livro Unico"))
                .andExpect(jsonPath("$.autor.nome").value("Autor Unico"))
                .andReturn();

        Livro responseLivro = objectMapper.readValue(result.getResponse().getContentAsString(), Livro.class);
        System.out.println("Livro retornado por ID: " + responseLivro.getTitulo());
    }

    @Test
    void deveListarLivros() throws Exception {
        Livro livro = new Livro("Livro Teste", new Autor("Autor Teste"), 2023);
        when(listarUseCase.listarTodos()).thenReturn(List.of(livro));

        MvcResult result = mockMvc.perform(get("/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Livro Teste"))
                .andExpect(jsonPath("$[0].autor.nome").value("Autor Teste"))
                .andReturn();

        List<Livro> responseLivros = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<Livro>>() {}
        );

        System.out.println("Livros retornados:");
        responseLivros.forEach(l -> System.out.println(" - " + l.getTitulo() + " (" + l.getAnoPublicacao() + ")"));
    }

}
