package arquetipo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import arquetipo.model.Environment;

@Service
public class EnvironmentService implements IEnvironmentService{
    public List<Environment> getEnvironments() {
        List<Environment> environments = new ArrayList<>();

        //Leer el fichero environments.yml
        

        return environments;
    }
}
