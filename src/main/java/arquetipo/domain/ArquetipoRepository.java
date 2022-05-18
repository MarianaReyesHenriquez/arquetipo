package arquetipo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import arquetipo.model.Arquetipo;

public interface ArquetipoRepository extends JpaRepository<Arquetipo, Long>{
    
}
