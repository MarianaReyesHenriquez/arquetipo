package arquetipo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arquetipo.model.ApplicationProps;
import arquetipo.model.Environment;

@Service
public class EnvironmentService implements IEnvironmentService{
    @Autowired
    private ApplicationProps applicationProps;
    
    public List<Environment> getEnvironments() {
        List<Environment> environmentList = new ArrayList<>();
        int numEnvironments = applicationProps.getEnvironment().size();

        for (int i = 0; i < numEnvironments; i++) {
            environmentList.add(applicationProps.getEnvironment().get(i));
        }

        return environmentList;
    }
}
