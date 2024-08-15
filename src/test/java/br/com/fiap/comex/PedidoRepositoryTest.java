package br.com.fiap.comex;

import br.com.fiap.comex.model.cliente.Cliente;
import br.com.fiap.comex.model.cliente.Endereco;
import br.com.fiap.comex.model.pedido.Pedido;
import br.com.fiap.comex.model.pedido.desconto.TipoDescontoEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class PedidoRepositoryTest {

    @Test
    public void SejaPossivelCriarUmPedidoSemProdutos() {

        Endereco endereco = new Endereco("Rua Sem Nome",
                "122",
                "Ao lado da padaria",
                "Planalto",
                "São Bernardo do Campo",
                "SP");

        Cliente cliente = new Cliente("João Alberto", "11111111111", "11989999999", endereco);
        Pedido novoPedido = new Pedido(cliente, TipoDescontoEnum.NENHUM);

        Assert.assertEquals(novoPedido.getCliente().getNome(), "João Alberto");
        Assert.assertEquals(novoPedido.getCliente().getCPF(), "11111111111");
    }

    @Test
    public void testScenario2() {

    }
}
