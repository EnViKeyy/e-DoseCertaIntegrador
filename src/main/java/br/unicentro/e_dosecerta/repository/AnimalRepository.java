package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.Animal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AnimalRepository extends CrudRepository<Animal, String> {

    @Query("select a from Animal a where  a.animalId = :animalId")
    public Animal findByAnimalId(@Param("animalId") Integer animalId);

    @Query("select a from Animal a where  a.rg = :rg")
    public Animal findByRg(@Param("rg") String rg);
}
