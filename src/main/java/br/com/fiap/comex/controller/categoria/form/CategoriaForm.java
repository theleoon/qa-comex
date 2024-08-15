package br.com.fiap.comex.controller.categoria.form;

import br.com.fiap.comex.model.categoria.Categoria;
import br.com.fiap.comex.model.categoria.CategoriaStatusEnum;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class CategoriaForm {

    @NotEmpty
    @NotNull
    @Length(min = 2)
    private String nome;

    public CategoriaForm(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria converter() {
        return new Categoria(this.nome, CategoriaStatusEnum.ATIVA);
    }
}
