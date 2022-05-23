package arquetipo.model;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import arquetipo.model.factory.YamlPropertySourceFactory;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "environments")
@PropertySource(value = "classpath:environments.yml", factory = YamlPropertySourceFactory.class)
public class ApplicationProps {
    private List<Environment> environment;
}
