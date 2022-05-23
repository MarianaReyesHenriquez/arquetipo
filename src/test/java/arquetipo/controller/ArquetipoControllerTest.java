package arquetipo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import arquetipo.model.Arquetipo;
import arquetipo.service.ArquetipoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArquetipoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ArquetipoService arquetipoService;


    @Test
    void crearArquetipo_returnCreated() throws Exception {
        Arquetipo arquetipo1 = new Arquetipo("Prueba de arquetipo");

        Mockito.when(arquetipoService.guardarArquetipo(any(Arquetipo.class))).thenReturn(arquetipo1);

        mockMvc.perform(post("/arquetipo/")
                .content(objectMapper.writeValueAsString(arquetipo1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msj", is("Prueba de arquetipo")));
        verify(arquetipoService, times(1)).guardarArquetipo(any(Arquetipo.class));

    }

    @Test
    void testRecuperarArquetipos() throws Exception {
        List<Arquetipo> arquetipos = Arrays.asList(
                new Arquetipo("Primer arquetipo"),
                new Arquetipo("Segundo arquetipo"));
        Mockito.when(arquetipoService.recuperarTodosArquetipos()).thenReturn(arquetipos);

        mockMvc.perform(get("/arquetipo/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].msj", is("Primer arquetipo")))
            .andExpect(jsonPath("$[1].msj", is("Segundo arquetipo")));

        verify(arquetipoService, times(1)).recuperarTodosArquetipos();

    }

    @Test
    void testRecuperarArquetipo() throws Exception {
        Arquetipo arquetipo = new Arquetipo("Prueba arquetipo");

        Mockito.when(arquetipoService.recuperarArquetipo((long) 1)).thenReturn(arquetipo);
        mockMvc.perform(get("/arquetipo/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.msj", is("Prueba arquetipo")));

        verify(arquetipoService, times(1)).recuperarArquetipo((long) 1);
    }

    @Test
    void testActualizarArquetipo() throws Exception {
        Arquetipo arquetipoOriginal = new Arquetipo("Arquetipo original");
        Arquetipo arquetipoModificada = new Arquetipo("Arquetipo modificado");

        Mockito.when(arquetipoService.recuperarArquetipo((long) 1)).thenReturn(arquetipoOriginal);

        Mockito.when(arquetipoService.modificarArquetipo(any(Arquetipo.class), any(Long.class))).thenReturn(arquetipoModificada);
        
        mockMvc.perform(put("/arquetipo/1")
            .content(objectMapper.writeValueAsString(arquetipoModificada))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.msj", is("Arquetipo modificado")));

        verify(arquetipoService , times(1)).modificarArquetipo(any(Arquetipo.class), any(Long.class));
    }

    @Test
    void testBorrarTodosArquetipos() throws Exception {
        doNothing().when(arquetipoService).borrarTodosArquetipos();
        
        mockMvc.perform(delete("/arquetipo/")).andExpect(status().isOk());
        verify(arquetipoService, times(1)).borrarTodosArquetipos();
    }

    @Test
    void testBorrarArquetipo() throws Exception {
        doNothing().when(arquetipoService).borrarArquetipo(anyLong());
        
        mockMvc.perform(delete("/arquetipo/1")).andExpect(status().isOk());
        verify(arquetipoService, times(1)).borrarArquetipo((long) 1);
    }

}
