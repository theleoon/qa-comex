package br.com.fiap.comex.repository.cliente;

import br.com.fiap.comex.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Cliente findByNome(String nome);

    @Query(value = "SELECT count(p.cliente_id) " +
            "FROM pedido p " +
            "WHERE p.cliente_id = :idCliente " +
            "GROUP BY p.cliente_id", nativeQuery = true)
    int findTotalDePedidos(Long idCliente);
}
