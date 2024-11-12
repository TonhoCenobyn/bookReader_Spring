package si.ufsm.bookReader.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Operation;
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
import si.ufsm.bookReader.model.cliente.Cliente;
import si.ufsm.bookReader.service.ClienteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Clientes", description = "Path da operacao com clientes")
public class ClienteController {
    private final ClienteService service;
    public ClienteController(ClienteService servico) {
        this.service = servico;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar clientes", description = "Lista todos os clientes da base")
    public List<Cliente> listar(){
        return this.service.Listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recuperar cliente", description = "Encontra um cliente pelo seu ID")
    public Cliente cliente(@PathVariable Long id){
        return this.service.getCliente(id);
    }

    @PostMapping()
    @Operation(summary = "Criar um novo cliente", description = "Cria um novo usuario e o insere na lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @   ApiResponse(responseCode = "400", description = "Dados invalidos fornecidos", content = @Content)
    })
    public ResponseEntity salvar(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder){
        this.service.Salvar(cliente);

        URI uri = uriBuilder.path("/cliente/{uuid}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping
    @Operation(summary = "Atualizar Cliente", description = "Atualiza um cliente pelo seu ID")
    public ResponseEntity atualizar(@RequestBody Cliente cliente){
        this.service.Atualizar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Cliente", description = "Deleta um cliente pelo seu ID")
    public ResponseEntity deletar(@PathVariable Long id){
        this.service.Excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(summary = "Recuperar cliente UUID", description = "Encontra um cliente pelo seu UUID")
    public Cliente cliente(@PathVariable String uuid){
        return this.service.getClienteUUID(uuid);
    }

    @PutMapping("/uuid")
    @Operation(summary = "Atualizar cliente UUID", description = "Atualiza um cliente pelo seu UUID")
    public void atualizarUuid(@RequestBody Cliente cliente){
        this.service.AtualizarUUID(cliente);
    }

    @Transactional
    @DeleteMapping("/uuid/{uuid}")
    @Operation(summary = "Deletar cliente UUID", description = "Deletar um cliente pelo seu UUID")
    public void deletarUuid(@PathVariable String uuid){
        this.service.DeletarUUID(uuid);
    }

    @PutMapping("/{idCliente}/atribuir-livros")
    @Transactional
    @Operation(summary = "Atribuir Livros Cliente", description = "Atribui livros a um cliente qualquer")
    public ResponseEntity<String> atribuirLivrosAoCliente(@PathVariable Long idCliente, @RequestBody List<Long> idLivros) {
        String resultado = service.atribuirLivrosAoCliente(idCliente, idLivros);
        return ResponseEntity.ok(resultado);
    }
}
