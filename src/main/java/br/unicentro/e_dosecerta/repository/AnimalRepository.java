package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, String> {

    public Animal findByAnimalId(Integer animalId);
}
