package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.FarmacoEspecie;
import org.springframework.data.repository.CrudRepository;

public interface FarmacoEspecieRepository extends CrudRepository<FarmacoEspecie, String> {

    public Iterable<FarmacoEspecie> findByEspecieId(Integer especieId);
}
