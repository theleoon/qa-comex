package br.com.fiap.comex.controller.produto.form;

import br.com.fiap.comex.model.produto.Produto;
import br.com.fiap.comex.service.categoria.CategoriaService;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProdutoForm {

    @Length(min = 2)
    private String nome;

    @Positive
    private BigDecimal preco;
    private String descricao;

    private int qntdEmEstoque;

    private Long idCategoria;

    public ProdutoForm(String nome, BigDecimal preco, String descricao, int qntdEmEstoque, Long idCategoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.qntdEmEstoque = qntdEmEstoque;
        this.idCategoria = idCategoria;
    }

    public ProdutoForm() {}

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQntdEmEstoque() {
        return qntdEmEstoque;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }


    public Produto converter(CategoriaService categoriaService) {
        return new Produto(nome, preco, descricao, qntdEmEstoque, categoriaService.getById(idCategoria).get());
    }
}
