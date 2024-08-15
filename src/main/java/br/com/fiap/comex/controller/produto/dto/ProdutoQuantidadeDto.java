package br.com.fiap.comex.controller.produto.dto;

import br.com.fiap.comex.model.pedido.ItemDePedido;
import br.com.fiap.comex.model.produto.Produto;
import br.com.fiap.comex.model.pedido.desconto.TipoDescontoEnum;
import br.com.fiap.comex.service.produto.ProdutoService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoQuantidadeDto {

    @NotNull
    private Long idProduto;

    @Min(1)
    private int quantidade;

    public ProdutoQuantidadeDto(Long idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean valido(ProdutoService service){
        Optional<Produto> recoverd = service.getById(idProduto);
        return (recoverd.isPresent() && recoverd.get().getQntEmEstoque()>0);
    }

    public ItemDePedido convertToItemPedido(ProdutoService service){
        Optional<Produto> recoverd = service.getById(idProduto);
        BigDecimal desconto = BigDecimal.ZERO;
        return new ItemDePedido(quantidade, recoverd.get(), desconto, TipoDescontoEnum.QUANTIDADE);
    }
}
