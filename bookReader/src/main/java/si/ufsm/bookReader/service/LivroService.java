package si.ufsm.bookReader.service;

import org.springframework.stereotype.Service;
import si.ufsm.bookReader.model.cliente.Cliente;
import si.ufsm.bookReader.model.cliente.ClienteRepository;
import si.ufsm.bookReader.model.livro.Livro;
import si.ufsm.bookReader.model.livro.LivroRepository;

import java.util.List;
import java.util.UUID;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final ClienteRepository clienteRepository;

    public LivroService(LivroRepository livroRepository, ClienteRepository clienteRepository){
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
    }

    public void Salvar(Livro livro){
        this.livroRepository.save(livro);
    }

    public List<Livro> Listar(){
        return this.livroRepository.findAll();
    }

    public Livro getLivro(Long id){
        return this.livroRepository.findById(id).get();
    }

    public Livro findById(Long id){
        return this.livroRepository.findById(id).get();
    }

    public void Atualizar(Livro livro){
        Livro lvr = this.livroRepository.findById(livro.getId()).orElse(null);
        lvr.setTitulo(livro.getTitulo());
        lvr.setAutor(livro.getAutor());
        this.livroRepository.save(lvr);
    }

    public void Excluir(Long id){
        this.livroRepository.deleteById(id);
    }

    public void AtualizarUUID(Livro livro){
        Livro lvr = this.livroRepository.findLivroByUuid(livro.getUuid());
        lvr.setTitulo(livro.getTitulo());
        lvr.setAutor(livro.getAutor());
        this.livroRepository.save(lvr);
    }

    public Livro getLivroUuid(String uuid){
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.livroRepository.findLivroByUuid(uuidFormatado);
    }

    public void DeletarUUID(String uuid){
        this.livroRepository.deleteLivroByUuid(UUID.fromString(uuid));
    }
}
