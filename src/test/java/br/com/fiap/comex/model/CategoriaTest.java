package br.com.fiap.comex.model;

import br.com.fiap.comex.ComexApplication;
import br.com.fiap.comex.model.categoria.Categoria;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ContextConfiguration(classes = ComexApplication.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaTest {

    private Categoria categoria;

    @Test
    public void testScenario1() {
        Long valor1 = Long.valueOf(1L);
        Long valor2 = Long.valueOf(3L);

        Long soma = valor1 + valor2;

        Assert.assertEquals(soma, Long.valueOf(4L));
    }

    @Test
    public void testScenario2() {
        String fraseParte1 = "Bem vindo ao ";
        String fraseParte2 = "Comex";

        String fraseCompleta = fraseParte1.concat(fraseParte2);

        Assert.assertEquals(fraseCompleta, "Bem vindo ao Comex");
    }
}
