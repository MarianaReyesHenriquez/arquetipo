package arquetipo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Entity
@Table(name = "arquetipo")
public class Arquetipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String msj;

    public Arquetipo(String msj) {
        this.msj = msj;
    }
}
