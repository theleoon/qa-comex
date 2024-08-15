package br.com.fiap.comex.repository.pedido;

import br.com.fiap.comex.model.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findByClienteNome(String nome);

}
