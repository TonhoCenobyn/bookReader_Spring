package si.ufsm.bookReader.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import si.ufsm.bookReader.model.cliente.Cliente;
import si.ufsm.bookReader.model.cliente.ClienteRepository;
import si.ufsm.bookReader.model.livro.Livro;
import si.ufsm.bookReader.model.livro.LivroRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final LivroRepository livroRepository;

    public ClienteService(ClienteRepository clienteRepository, LivroRepository livroRepository) {
        this.clienteRepository = clienteRepository;
        this.livroRepository = livroRepository;
    }

    public void Salvar(Cliente cliente){
        cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
        this.clienteRepository.save(cliente);
    }

    public List<Cliente> Listar(){
        return this.clienteRepository.findAll();
    }

    public Cliente getCliente(Long id){
        return this.clienteRepository.findById(id).get();
    }

    public Cliente findById(Long id){
        return this.clienteRepository.findById(id).get();
    }

    public void Atualizar(Cliente cliente){
        Cliente cl = this.clienteRepository.findById(cliente.getId()).orElse(null);
        cl.setNome(cliente.getNome());
        cl.setEmail(cliente.getEmail());
        cl.setSenha(cliente.getSenha());
        this.clienteRepository.save(cl);
    }

    public void Excluir(Long id){
        this.clienteRepository.deleteById(id);
    }

    public void AtualizarUUID(Cliente cliente){
        Cliente a = this.clienteRepository.findClienteByUuid(cliente.getUuid());
        a.setNome(cliente.getNome());
        a.setEmail(cliente.getEmail());
        a.setSenha(cliente.getSenha());

        this.clienteRepository.save(a);
    }

    public Cliente getClienteUUID(String uuid){
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.clienteRepository.findClienteByUuid(uuidFormatado);
    }

    public void DeletarUUID(String uuid){
        this.clienteRepository.deleteClienteByUuid(UUID.fromString(uuid));
    }

    public String atribuirLivrosAoCliente(Long idCliente, List<Long> idLivros) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        List<Livro> livros = livroRepository.findAllById(idLivros);

        if (!livros.isEmpty()) {
            cliente.setLivros(livros);
            clienteRepository.save(cliente);
            return "Livros atribuídos ao cliente com sucesso!";
        } else {
            return "Lista de livros vazia ou IDs inválidos";
        }
    }
}
