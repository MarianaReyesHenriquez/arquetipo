package arquetipo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arquetipo.model.Environment;
import arquetipo.service.IEnvironmentService;

@RestController
@RequestMapping("environments")
public class EnvironmentsController {
    Logger logger = LoggerFactory.getLogger(EnvironmentsController.class);

    EnvironmentsController() {
    }

    @Autowired
    private IEnvironmentService environmentService;

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Environment> getEnvironments() {
        logger.info("Se ha entrado por GET/environments");
        return environmentService.getEnvironments();
    }
    
}
