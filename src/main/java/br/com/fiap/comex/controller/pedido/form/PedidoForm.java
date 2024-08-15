package br.com.fiap.comex.controller.pedido.form;

import br.com.fiap.comex.controller.produto.dto.ProdutoQuantidadeDto;
import br.com.fiap.comex.model.cliente.Cliente;
import br.com.fiap.comex.model.pedido.ItemDePedido;
import br.com.fiap.comex.model.pedido.Pedido;
import br.com.fiap.comex.model.pedido.desconto.TipoDescontoEnum;
import br.com.fiap.comex.service.cliente.ClienteService;
import br.com.fiap.comex.service.pedido.PedidoService;
import br.com.fiap.comex.service.produto.ProdutoService;

import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PedidoForm {

    @NotNull
    private Long idCliente;
    private List<ProdutoQuantidadeDto> listProdutoQntd;

    public PedidoForm(Long idCliente, List<ProdutoQuantidadeDto> listProdutoQntd) {
        this.idCliente = idCliente;
        this.listProdutoQntd = listProdutoQntd;
    }

    public PedidoForm(Long idCliente) {
        this.idCliente = idCliente;
        this.listProdutoQntd = new ArrayList<>();
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public List<ProdutoQuantidadeDto> getListProdutoQntd() {
        return listProdutoQntd;
    }

    public boolean valido(ClienteService clienteService, ProdutoService produtoService) {
        for (ProdutoQuantidadeDto pq: listProdutoQntd) {
            if(!pq.valido(produtoService)) return false;
        }
        return clienteService.getById(idCliente) != null;
    }

    public Pedido converter(ClienteService clienteService, ProdutoService produtoService, PedidoService pedidoService) {
        Cliente cliente = clienteService.getById(idCliente).get();
        Pedido novo = new Pedido(cliente, TipoDescontoEnum.NENHUM);
        pedidoService.aplicarDescontos(novo);

        for (ProdutoQuantidadeDto pq: listProdutoQntd) {
            ItemDePedido item = pq.convertToItemPedido(produtoService);
            novo.adicionarItem(item);
        }
        return novo;
    }
}
