package br.com.fiap.comex.controller.pedido.dto;

import br.com.fiap.comex.model.pedido.Pedido;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoDetailsDto {
    private LocalDate data;
    @JsonProperty("Valor Total")
    private BigDecimal valorTotal;

    @JsonProperty("Desconto Totais")
    private BigDecimal desconto;

    private List<ItemPedidoDto> itens;

    @JsonProperty("ID do Cliente")
    private Long idCliente;

    @JsonProperty("Nome do Cliente")
    private String nomeCliente;

    public PedidoDetailsDto(Pedido pedido) {
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorLiquido();
        this.desconto = pedido.getDesconto();
        pedido.getItens().forEach(item -> {
            ItemPedidoDto dto = new ItemPedidoDto(item);
            this.itens.add(dto);
        });
        this.idCliente = pedido.getCliente().getId();
        this.nomeCliente = pedido.getCliente().getNome();
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public List<ItemPedidoDto> getItens() {
        return itens;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
}
