package br.com.fiap.comex.model;

import br.com.fiap.comex.ComexApplication;
import br.com.fiap.comex.model.categoria.Categoria;
import br.com.fiap.comex.model.categoria.CategoriaStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ComexApplication.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaTest {

    private Categoria categoria;

    /*
    *   Seja possível criar uma categoria informando apenas um nome válido.
        Seja possível criar uma categoria informando nome e status.
        Não seja possível criar uma categoria com nome vazio.
    * */

    @Test
    public void sejaPossivelCriarUmaCategoriaComNomeValido() {
        String nomeDaCategoria = "Informatica";

        Categoria novaCategoria = new Categoria(nomeDaCategoria);
        Assert.assertEquals(nomeDaCategoria, novaCategoria.getNome());
    }

    @Test
    public void sejaPossivelCriarUmaCategoriaComNomeEStatusValido() {
        String nomeDaCategoria = "Informatica";
        CategoriaStatusEnum status = CategoriaStatusEnum.INATIVA;

        Categoria novaCategoria = new Categoria(nomeDaCategoria, status);

        Assert.assertEquals(nomeDaCategoria, novaCategoria.getNome());
        Assert.assertEquals(status, novaCategoria.getStatus());
    }

    @Test
    public void naoSejaPossivelCriarUmaCategoriaComNomeVazio(){
        String nomeDaCategoria = "";
//        boolean erro = false;
//
//        try {
//            Categoria novaCategoria = new Categoria(nomeDaCategoria);
//        } catch (IllegalArgumentException ex) {
//           erro = true;
//        }
//
//        Assert.assertEquals(true, erro);

        Assert.assertThrows(java.lang.IllegalArgumentException.class, () -> new Categoria(nomeDaCategoria));
    }

}
