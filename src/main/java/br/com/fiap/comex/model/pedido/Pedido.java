package br.com.fiap.comex.model.pedido;

import br.com.fiap.comex.model.pedido.desconto.TipoDescontoEnum;
import br.com.fiap.comex.model.cliente.Cliente;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name="pedido")
public class Pedido{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemDePedido> itens = new ArrayList<>();

    @Column(name = "desconto", nullable = false, scale = 2)
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column(name = "tipo_desconto", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDescontoEnum tipoDesconto = TipoDescontoEnum.NENHUM;

    public Pedido() {
    }

    public Pedido(Cliente cliente, TipoDescontoEnum tipoDesconto) {
        this.cliente = cliente;
        aplicaDesconto(tipoDesconto);
    }

    public void aplicaDesconto(TipoDescontoEnum tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
        this.desconto = getValorLiquido().multiply(tipoDesconto.percentual());
    }

    public BigDecimal getValorBruto(){
        BigDecimal total = BigDecimal.ZERO;
        for (ItemDePedido item : itens) {
            total = total.add(item.getPrecoUnitario().subtract(item.getDesconto()));
        }
        return total;
    }

    public BigDecimal getValorLiquido(){
        BigDecimal total = BigDecimal.ZERO;
        for (ItemDePedido item : itens) {
            total = total.add(item.getPrecoUnitario().subtract(item.getDesconto()));
        }
        return total.subtract(this.desconto);
    }

    public void addItems(List<ItemDePedido> itens) {
        this.itens.addAll(itens);
    }

    public void addItem(ItemDePedido item) {
        this.itens.add(item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemDePedido> getItens() {
        return itens;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDescontoEnum getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDescontoEnum tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public int getQuantidadeItens() {
        return this.itens.size();
    }
}
