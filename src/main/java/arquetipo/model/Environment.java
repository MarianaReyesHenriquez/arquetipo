package arquetipo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @JsonIgnore
    private String url;
    @JsonIgnore
    private String user;
    @JsonIgnore
    private String pass;
    @JsonIgnore
    private String authMethod;
}
