package si.ufsm.bookReader.model.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findClienteByUuid(UUID uuid);
    public void deleteClienteByUuid(UUID uuid);
    public Cliente findByEmail(String email);

}
