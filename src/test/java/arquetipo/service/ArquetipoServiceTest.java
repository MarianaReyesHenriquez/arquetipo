package arquetipo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import arquetipo.model.Arquetipo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArquetipoServiceTest {

    @Autowired
    ArquetipoService arquetipoService;

    private Arquetipo arquetipo1;
    private Arquetipo arquetipo2;

    @BeforeEach
    public void inicioTest() {
        Arquetipo _arquetipo1 = new Arquetipo("Arquetipo uno");
        arquetipo1 = arquetipoService.guardarArquetipo(_arquetipo1);
        Arquetipo _arquetipo2 = new Arquetipo("Arquetipo dos");
        arquetipo2 = arquetipoService.guardarArquetipo(_arquetipo2);
    }

    @AfterEach
    public void finalTest() {
        arquetipoService.borrarTodosArquetipos();
    }

    @Test
    void testGuardarArquetipoReturnArquetipo() throws Exception {
        
        assertEquals("Arquetipo uno", arquetipo1.getMsj());
    }

    @Test
    void testRecuperarArquetiposReturnListaArquetipos() throws Exception {

        List<Arquetipo> arquetiposEsperados = Arrays.asList(arquetipo1, arquetipo2);
        
        List<Arquetipo> actualArquetipos = arquetipoService.recuperarTodosArquetipos();
        
        assertEquals(arquetiposEsperados.size(), actualArquetipos.size());
        assertEquals(arquetiposEsperados.get(0).getMsj(), actualArquetipos.get(0).getMsj());
        assertEquals(arquetiposEsperados.get(1).getMsj(), actualArquetipos.get(1).getMsj());
    }

    @Test
    void testRecuperarArquetipoReturnArquetipo() throws Exception {
        
        Arquetipo actualNota = arquetipoService.recuperarArquetipo((long) 1);
        
        assertEquals("Arquetipo uno", actualNota.getMsj());
    }

}
