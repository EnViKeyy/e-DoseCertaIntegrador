package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.Dosagem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DosagemRepository extends CrudRepository<Dosagem, String> {

    @Query("select d from Dosagem d where d.veterinarioId = :veterinarioId")
    public Dosagem findByVeterinarioId(@Param("veterinarioId") Integer veterinarioId);
}
