package br.com.fiap.comex.repository;

import br.com.fiap.comex.ComexApplication;
import br.com.fiap.comex.model.categoria.Categoria;
import br.com.fiap.comex.model.categoria.CategoriaStatusEnum;
import br.com.fiap.comex.repository.categoria.CategoriaRepository;
import br.com.fiap.comex.service.categoria.CategoriaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = ComexApplication.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaRepositoryTest {
    private Categoria informatica;
    private Categoria eletronico;
    private Categoria celular;
    private List<Categoria> categorias = new ArrayList<>();
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaService categoriaService;

    @BeforeAll
    public void build() {
        informatica = new Categoria("INFO");
        categorias.add(informatica);
        eletronico = new Categoria("ELETRO");
        categorias.add(eletronico);
        celular = new Categoria("CELULAR", CategoriaStatusEnum.INATIVA);
        categorias.add(celular);

        categoriaRepository.saveAll(categorias);
    }

    @Test
    void deveriaCarregarTodasAsCategoriasDoBancoDeDados() {
        List<Categoria> foundCategorias = categoriaRepository.findAll();

        assertArrayEquals(categorias.toArray(), foundCategorias.toArray());
    }

    @Test
    void deveriaAdicionarUmaNovaCategoria() {
        Categoria novaCategoria = new Categoria("Móveis");
        categoriaRepository.save(novaCategoria);

        assertEquals(novaCategoria, categoriaRepository.findByNome("Móveis"));
    }

    @Test
    void deveriaAdicionarUmaNovaCategoria2() {
        Categoria novaCategoria = new Categoria("Móveis");
        categoriaService.cadastra(novaCategoria);

        assertEquals(novaCategoria, categoriaRepository.findByNome("Móveis"));
    }

}
