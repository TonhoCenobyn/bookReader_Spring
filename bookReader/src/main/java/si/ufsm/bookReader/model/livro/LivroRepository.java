package si.ufsm.bookReader.model.livro;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import si.ufsm.bookReader.model.cliente.Cliente;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    public Livro findLivroByUuid(UUID uuid);
    public void deleteLivroByUuid(UUID uuid);
}
