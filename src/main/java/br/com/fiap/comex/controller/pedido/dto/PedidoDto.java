package br.com.fiap.comex.controller.pedido.dto;

import br.com.fiap.comex.model.pedido.ItemDePedido;
import br.com.fiap.comex.model.pedido.Pedido;
import br.com.fiap.comex.model.pedido.desconto.TipoDescontoEnum;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDto {
    private Long id;
    private Long idCliente;
    private BigDecimal descontoNoPedido;
    private TipoDescontoEnum tipoDesconto;
    private List<ItemDePedido> itemDePedidos;

    public PedidoDto(Pedido novo) {
        this.id = novo.getId();
        this.idCliente = novo.getCliente().getId();
        this.descontoNoPedido = novo.getDesconto();
        this.tipoDesconto = novo.getTipoDesconto();
        this.itemDePedidos = novo.getItens();
    }

    public Long getId() {
        return id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public BigDecimal getDescontoNoPedido() {
        return descontoNoPedido;
    }

    public TipoDescontoEnum getTipoDesconto() {
        return tipoDesconto;
    }

    public List<ItemDePedido> getItemDePedidos() {
        return itemDePedidos;
    }
}
