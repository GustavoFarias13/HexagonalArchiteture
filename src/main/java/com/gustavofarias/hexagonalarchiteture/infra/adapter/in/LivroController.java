package com.gustavofarias.hexagonalarchiteture.infra.adapter.in;

import com.gustavofarias.hexagonalarchiteture.application.port.in.BuscarLivroPorIdUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.CadastrarLivroUseCase;
import com.gustavofarias.hexagonalarchiteture.application.port.in.ListarLivrosUseCase;
import com.gustavofarias.hexagonalarchiteture.domain.Livro;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final CadastrarLivroUseCase cadastrarUseCase;
    private final BuscarLivroPorIdUseCase buscarUseCase;
    private final ListarLivrosUseCase listarUseCase;

    public LivroController(CadastrarLivroUseCase cadastrarUseCase,
                           BuscarLivroPorIdUseCase buscarUseCase,
                           ListarLivrosUseCase listarUseCase) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.buscarUseCase = buscarUseCase;
        this.listarUseCase = listarUseCase;
    }

    @PostMapping
    public Livro cadastrar(@RequestBody Livro livro) {
        return cadastrarUseCase.cadastrarLivro(livro);
    }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable UUID id) {
        return buscarUseCase.buscarPorId(id);
    }

    @GetMapping
    public List<Livro> listar() {
        return listarUseCase.listarTodos();
    }
}
