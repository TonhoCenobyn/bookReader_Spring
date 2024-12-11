package si.ufsm.bookReader.service;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import si.ufsm.bookReader.model.cliente.Cliente;
import si.ufsm.bookReader.model.cliente.ClienteRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
    private final ClienteRepository repository;

    public AutenticacaoService(ClienteRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = this.repository.findByEmail(email);
        if (cliente == null){
            throw new UsernameNotFoundException("Usuario ou senha incorretos");
        }else {
            UserDetails user = User.withUsername(cliente.getEmail()).password(cliente.getSenha()).authorities(cliente.getPermissao()).build();
            return user;
        }
    }
}
