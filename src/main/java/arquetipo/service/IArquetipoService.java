package arquetipo.service;

import java.util.List;

import arquetipo.model.Arquetipo;

public interface IArquetipoService {
    Arquetipo guardarArquetipo(Arquetipo arquetipo);
    List<Arquetipo> recuperarTodosArquetipos();
    Arquetipo recuperarArquetipo(Long id);
    Arquetipo modificarArquetipo(Arquetipo arquetipo, Long id);
    void borrarTodosArquetipos();
    void borrarArquetipo(Long id);
}
