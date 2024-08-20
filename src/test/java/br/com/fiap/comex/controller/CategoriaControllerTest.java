package br.com.fiap.comex.controller;


import br.com.fiap.comex.controller.categoria.dto.CategoriaDto;
import br.com.fiap.comex.model.categoria.Categoria;
import br.com.fiap.comex.model.categoria.CategoriaStatusEnum;
import br.com.fiap.comex.repository.categoria.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.core.type.TypeReference;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaControllerTest {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    @Transactional
    public void build() {
        List<Categoria> categorias = new ArrayList<>();

        Categoria informatica = new Categoria("INFO");
        categorias.add(informatica);
        Categoria eletronico = new Categoria("ELETRO");
        categorias.add(eletronico);
        Categoria celular = new Categoria("CELULAR", CategoriaStatusEnum.INATIVA);
        categorias.add(celular);

        categoriaRepository.saveAll(categorias);
    }

    @Test
    public void deveriaCarregarCategoriaPorId() throws Exception {
        Long idCategoria = 1L;
        String nomeCategoria = "INFO";

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/categorias/" + idCategoria))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        System.out.println("deveriaCarregarCategoriaPorId " + response);

        CategoriaDto categoriaResponseDto = mapper.readValue(response, new TypeReference<CategoriaDto>() {});

        assertEquals(nomeCategoria, categoriaResponseDto.getNome());
        assertEquals(idCategoria, categoriaResponseDto.getId());
    }

}
