package si.ufsm.bookReader.model.livro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import si.ufsm.bookReader.model.cliente.Cliente;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UuidGenerator
    private UUID uuid;

    @NonNull
    private String titulo;

    @NonNull
    private String autor;

    /*@ManyToMany(mappedBy = "livros")
    @JsonIgnore
    private List<Cliente> clientes;*/
}
