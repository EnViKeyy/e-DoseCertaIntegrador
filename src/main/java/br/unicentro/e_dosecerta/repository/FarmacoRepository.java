package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.Farmaco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FarmacoRepository extends CrudRepository<Farmaco, String> {

    @Query("select f from Farmaco f where  f.farmacoId = :farmacoId")
    public Farmaco findByFarmacoId(@Param("farmacoId") Integer farmacoId);
}
