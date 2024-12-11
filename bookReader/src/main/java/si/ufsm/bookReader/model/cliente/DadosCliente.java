package si.ufsm.bookReader.model.cliente;

public record DadosCliente(Long id, String email, String permissao) {
    public DadosCliente(Cliente cliente){
        this(cliente.getId(), cliente.getEmail(), cliente.getPermissao());}
}
