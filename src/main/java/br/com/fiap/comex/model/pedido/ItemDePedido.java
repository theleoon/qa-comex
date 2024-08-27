package br.com.fiap.comex.model.pedido;

import br.com.fiap.comex.model.pedido.desconto.TipoDescontoEnum;
import br.com.fiap.comex.model.produto.Produto;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name= "item_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "preco_unitario", nullable = false, scale = 2)
    private BigDecimal precoUnitario = BigDecimal.ZERO;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @JoinColumn(name = "pedido_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    @JoinColumn(name = "produto_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @Column(name = "desconto")
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column(name = "tipo_desconto", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDescontoEnum tipoDesconto;

    public ItemDePedido() {
    }

    public ItemDePedido(int quantidade, Produto produto, BigDecimal desconto, TipoDescontoEnum tipoDesconto) {
        this.precoUnitario = produto.getPrecoUnitario();
        this.quantidade = quantidade;
        this.produto = produto;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
        aplicarDesconto();
    }

    public void aplicarDesconto(){
        if(this.quantidade>10){
            this.desconto = new BigDecimal(0.1);
            this.tipoDesconto = TipoDescontoEnum.QUANTIDADE;
        }
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public TipoDescontoEnum getTipoDesconto() {
        return tipoDesconto;
    }

    public void setPedido(Pedido pedido) {
        this.pedido= pedido;
    }

    @Override
    public String toString() {
        return "ItemDePedido{" +
                "id=" + id +
                ", precoUnitario=" + precoUnitario +
                ", quantidade=" + quantidade +
                ", pedido=" + pedido +
                ", produto=" + produto +
                ", desconto=" + desconto +
                ", tipoDesconto=" + tipoDesconto +
                '}';
    }
}
