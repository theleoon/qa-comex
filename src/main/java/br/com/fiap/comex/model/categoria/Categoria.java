package br.com.fiap.comex.model.categoria;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoria")
public class Categoria  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaStatusEnum status;

    public Categoria(String nome,CategoriaStatusEnum status) {
        if(nome==null){
            throw new NullPointerException();
        }
        if(nome.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.nome = nome;
        this.status = status;
    }

    public Categoria(String nome) {
        if(nome==null){
            throw new NullPointerException();
        }
        if(nome.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.nome = nome;
        this.status = CategoriaStatusEnum.ATIVA;
    }

    public Categoria() {}

    public String getNome() {
        return nome;
    }

    public CategoriaStatusEnum getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString(){
        return "CATEGORIA: " + nome + "\n" +
                "QUANTIDADE: " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria that = (Categoria) o;
        return Objects.equals(nome, that.getNome());
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }
}

