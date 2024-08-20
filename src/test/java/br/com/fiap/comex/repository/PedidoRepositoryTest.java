package br.com.fiap.comex.repository;

import br.com.fiap.comex.ComexApplication;
import br.com.fiap.comex.model.cliente.Cliente;
import br.com.fiap.comex.model.cliente.Endereco;
import br.com.fiap.comex.model.pedido.Pedido;
import br.com.fiap.comex.model.pedido.desconto.TipoDescontoEnum;
import br.com.fiap.comex.repository.cliente.ClienteRepository;
import br.com.fiap.comex.repository.pedido.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = ComexApplication.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PedidoRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    public void deveriaCriarUmPedido() {

        Endereco endereco = new Endereco("Rua Sem Nome",
                "122",
                "Ao lado da padaria",
                "Planalto",
                "São Bernardo",
                "SP");

        Cliente cliente = new Cliente("João Alberto", "11111111111", "11989999999", endereco);
        clienteRepository.save(cliente);
        Pedido novoPedido = new Pedido(cliente, TipoDescontoEnum.NENHUM);
        pedidoRepository.save(novoPedido);

        Pedido pedidoCarregado = pedidoRepository.findByClienteNome("João Alberto");

        assertEquals(pedidoCarregado.getCliente().getNome(), "João Alberto");
        assertEquals(pedidoCarregado.getCliente().getCPF(), "11111111111");
    }

    @Test
    public void testScenario2() {

    }
}
