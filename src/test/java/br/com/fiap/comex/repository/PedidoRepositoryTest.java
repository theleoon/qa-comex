package br.com.fiap.comex.repository;

import br.com.fiap.comex.ComexApplication;
import br.com.fiap.comex.model.categoria.Categoria;
import br.com.fiap.comex.model.cliente.Cliente;
import br.com.fiap.comex.model.cliente.Endereco;
import br.com.fiap.comex.model.pedido.ItemDePedido;
import br.com.fiap.comex.model.pedido.Pedido;
import br.com.fiap.comex.model.pedido.desconto.TipoDescontoEnum;
import br.com.fiap.comex.model.produto.Produto;
import br.com.fiap.comex.repository.cliente.ClienteRepository;
import br.com.fiap.comex.repository.pedido.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

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

    // 10% de desconto para um pedido
    // aplicar até 10% de desconto sobre o total de um pedido.
    @Test
    public void deveriaAplicarAte10DeDescontoNoPedido() {

        Endereco endereco = new Endereco("Rua Sem Nome",
                "122",
                "Ao lado da padaria",
                "Planalto",
                "São Bernardo",
                "SP");

        Cliente cliente = new Cliente("João Alberto", "11111111111", "11989999999", endereco);

        Categoria categoriaCelulares = new Categoria("Celulares");

        Produto produto1 = new Produto("Celular Samsung",
                new BigDecimal("1000"),
                "Celular Samsung 128GB",
                10,
                categoriaCelulares);

        ItemDePedido itemDePedidoProduto1 = new ItemDePedido(1,
                produto1,
                BigDecimal.ZERO,
                TipoDescontoEnum.NENHUM);

        Pedido novoPedido = new Pedido(cliente, TipoDescontoEnum.NENHUM);
        novoPedido.addItem(itemDePedidoProduto1);

        BigDecimal valorTotalDoPedido = novoPedido.getValorLiquido();

        novoPedido.aplicaDesconto(TipoDescontoEnum.PROMOCAO);

        System.out.println("itens: "+ novoPedido.getItens());
        BigDecimal valorTotalDoPedidoComDesconto = novoPedido.getValorLiquido();

        // garanto que criou o pedido com o valor correto!
        assertEquals(new BigDecimal("1000"), valorTotalDoPedido.setScale(0));
        // garanto que aplicou os 10% de desconto no pedido!
        assertEquals(new BigDecimal("900"), valorTotalDoPedidoComDesconto.setScale(0));

    }
}
