package arquetipo.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arquetipo.model.Arquetipo;
import arquetipo.service.IArquetipoService;


@RestController
@RequestMapping("arquetipo")
public class ArquetipoController {
    Logger logger = LoggerFactory.getLogger(ArquetipoController.class);

    ArquetipoController() {
    }

    @Autowired
    private IArquetipoService arquetipoService;

    //Guardar un arquetipo, enviado en el cuerpo de la petición
    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Arquetipo guardarArquetipo(@RequestBody Arquetipo arquetipoPorGuardar) {
        logger.info("Se ha entrado por POST/arquetipo");
        return arquetipoService.guardarArquetipo(arquetipoPorGuardar);
    }

    //Devolver una lista con todos los arquetipos guardados
    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Arquetipo> devolverTodosArquetipo() {
        logger.info("Se ha entrado por GET/arquetipo/");
        return arquetipoService.recuperarTodosArquetipos();
    } 

    //Devolver un arquetipo en concreto
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Arquetipo devolverArquetipo(@PathVariable Long id) {
        logger.info("Se ha entrado por GET/arquetipo/");
        return arquetipoService.recuperarArquetipo(id);
    } 

    //Modificar un recordatorio pasando su id
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Arquetipo modificarArquetipo(@RequestBody Arquetipo arquetipoPorModificar, @PathVariable Long id) {
        logger.info("Se ha entrado por PUT/arquetipo/{id}");
        return arquetipoService.modificarArquetipo(arquetipoPorModificar, id);
    }

    //Borrar todos los recordatorios de una fecha en específico
    @DeleteMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    void borrarTodosArquetipos() {
        logger.info("Se ha entrado por DELETE/arquetipo/");
        arquetipoService.borrarTodosArquetipos();
    }

    //Borrar un recordatorio pasando su id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    void borrarArquetipo(@PathVariable Long id) {
        logger.info("Se ha entrado por DELETE/arquetipo/{id}");
        arquetipoService.borrarArquetipo(id);
    }
    
}
