package si.ufsm.bookReader.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import si.ufsm.bookReader.model.livro.Livro;
import si.ufsm.bookReader.service.LivroService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livro")
@Tag(name = "Livros", description = "Path da operacao com livros")
public class LivroController {
    private LivroService service;
    public LivroController(LivroService servico){
        this.service = servico;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar livros", description = "Lista todos os livros da base")
    public List<Livro> listar()
    {
        return this.service.Listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Retorna um usuário correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Livro.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public Livro livro( @Parameter(description = "ID do usuário a ser buscado") @PathVariable Long id){
        return this.service.getLivro(id);
    }

    @PostMapping()
    @Operation(summary = "Criar um novo livro", description = "Cria um novo livro e o adiciona à lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Livro.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content)
    })
    public ResponseEntity salvar(@RequestBody @Valid Livro livro, UriComponentsBuilder uriBuilder){
        this.service.Salvar(livro);

        URI uri = uriBuilder.path("/livro/{uuid}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).body(livro);
    }

    @PutMapping
    @Operation(summary = "Atualizar Livro", description = "Atualiza um livro pelo seu ID")
    public ResponseEntity atualizar(@RequestBody Livro livro){
        this.service.Atualizar(livro);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar livro", description = "Deleta um livro pelo seu ID")
    public ResponseEntity deletar(@PathVariable Long id){
        this.service.Excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/uuid/{uuid}")
    @Operation(summary = "Deletar livro UUID", description = "Deleta um livro pelo seu UUID")
    public void deletarUuid(@PathVariable String uuid){
        this.service.DeletarUUID(uuid);
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Recuperar livro UUID", description = "Recupera um livro pelo seu UUID")
    public Livro livro(@PathVariable String uuid){
        return this.service.getLivroUuid(uuid);
    }

    @PutMapping("/uuid")
    @Operation(summary = "Atualizar livro UUID", description = "Atualiza um livro pelo seu UUID")
    public void AtualizarUUID(@RequestBody Livro livro){
        this.service.AtualizarUUID(livro);
    }
}
