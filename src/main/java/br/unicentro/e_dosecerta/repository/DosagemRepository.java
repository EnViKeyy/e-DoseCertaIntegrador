package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.Dosagem;
import org.springframework.data.repository.CrudRepository;

public interface DosagemRepository extends CrudRepository<Dosagem, String> {

    public Dosagem findByDosagemId(Integer dosagemId);
}
