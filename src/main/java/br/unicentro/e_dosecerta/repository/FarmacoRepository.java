package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.Farmaco;
import org.springframework.data.repository.CrudRepository;

public interface FarmacoRepository extends CrudRepository<Farmaco, String> {

    public Farmaco findByFarmacoId(Integer farmacoId);
}
