package arquetipo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arquetipo.domain.ArquetipoRepository;
import arquetipo.exception.ArquetipoNotFoundException;
import arquetipo.model.Arquetipo;

@Service
public class ArquetipoService implements IArquetipoService {
    @Autowired
    private ArquetipoRepository repository;

    public ArquetipoService(ArquetipoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Arquetipo guardarArquetipo(Arquetipo arquetipo) {
        return repository.save(arquetipo);
    }

    @Transactional
    public List<Arquetipo> recuperarTodosArquetipos() {
        return repository.findAll();
    }

    @Transactional
    public Arquetipo recuperarArquetipo(Long id) {
        return repository.findById(id).orElseThrow(() -> new ArquetipoNotFoundException(id));
      }

    @Transactional
    public Arquetipo modificarArquetipo(Arquetipo arquetipo, Long id) {
        return repository.findById(id)
        .map(arquetipoActualizado -> {
            arquetipoActualizado.setMsj(arquetipo.getMsj());
            return repository.save(arquetipoActualizado);
        })
        .orElseThrow(() -> new ArquetipoNotFoundException(id));
    }

    @Transactional
    public void borrarTodosArquetipos() {
        repository.deleteAll();
    }

    @Transactional
    public void borrarArquetipo(Long id) {
        repository.deleteById(id);
    }
    
}
