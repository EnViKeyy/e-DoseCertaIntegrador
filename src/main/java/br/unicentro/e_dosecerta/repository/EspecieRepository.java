package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.Especie;
import org.springframework.data.repository.CrudRepository;

public interface EspecieRepository extends CrudRepository<Especie, String> {

    public Especie findByEspecieId(Integer especieId);
}
