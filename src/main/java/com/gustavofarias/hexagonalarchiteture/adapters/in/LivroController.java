package com.gustavofarias.hexagonalarchiteture.adapters.in;

import com.gustavofarias.hexagonalarchiteture.application.port.in.BuscarLivroPorIdUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.CadastrarLivroUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.ListarLivrosUseCase;
import com.gustavofarias.hexagonalarchiteture.domain.Autor;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final CadastrarLivroUseCase cadastrarLivro;
    private final BuscarLivroPorIdUseCase buscarLivro;
    private final ListarLivrosUseCase listarLivros;

    public LivroController(CadastrarLivroUseCase cadastrarLivro,
                           BuscarLivroPorIdUseCase buscarLivro,
                           ListarLivrosUseCase listarLivros) {
        this.cadastrarLivro = cadastrarLivro;
        this.buscarLivro = buscarLivro;
        this.listarLivros = listarLivros;
    }

    @PostMapping
    public Livro cadastrar(@RequestBody LivroDTO dto) {
        Livro livro = new Livro(
                UUID.randomUUID(),
                dto.titulo(),
                new Autor(UUID.randomUUID(), dto.autor()),
                dto.anoPublicacao()
        );
        return cadastrarLivro.cadastrar(livro);
    }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable UUID id) {
        return buscarLivro.buscarPorId(id);
    }

    @GetMapping
    public List<Livro> listar() {
        return listarLivros.listarTodos();
    }
}
