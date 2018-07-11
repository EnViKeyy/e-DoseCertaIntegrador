package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.FarmacoEspecie;
import br.unicentro.e_dosecerta.models.FarmacoEspeciePK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FarmacoEspecieRepository extends CrudRepository<FarmacoEspecie, String> {

    @Query("select fs from FarmacoEspecie fs where fs.farmacoEspeciePK = :farmacoEspeciePK")
    public FarmacoEspecie findByFarmacoEspecieId(@Param("farmacoEspeciePK") FarmacoEspeciePK farmacoEspeciePK);
    
    @Query("select fs from FarmacoEspecie fs where fs.farmacoEspeciePK.especieId = :especieId")
    public Iterable<FarmacoEspecie> findByEspecieId(@Param("especieId") Integer especieId);
    
    @Query("select fs from FarmacoEspecie fs where fs.farmacoEspeciePK.farmacoId = :farmacoId")
    public FarmacoEspecie findByFarmacoId(@Param("farmacoId") Integer farmacoId);
}
