package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.Especie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EspecieRepository extends CrudRepository<Especie, String> {

    @Query("select e from Especie e where e.especieId = :especieId")
    public Especie findByEspecieId(@Param("especieId") Integer especieId);
}
