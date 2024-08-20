package br.com.fiap.comex.controller.categoria.dto;

import br.com.fiap.comex.model.categoria.Categoria;
import br.com.fiap.comex.model.categoria.CategoriaStatusEnum;

public class CategoriaDto {

    private Long id;
    private String nome;
    private CategoriaStatusEnum status;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
    }

    public CategoriaDto(Long id, String nome, CategoriaStatusEnum status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
    }

    public CategoriaDto() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaStatusEnum getStatus() {
        return status;
    }
}
