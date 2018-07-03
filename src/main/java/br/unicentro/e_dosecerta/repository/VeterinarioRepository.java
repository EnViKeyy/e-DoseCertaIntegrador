package br.unicentro.e_dosecerta.repository;

import br.unicentro.e_dosecerta.models.Veterinario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VeterinarioRepository extends CrudRepository<Veterinario, String> {

    @Query("select v from Veterinario v where  v.email = :email")
    public Veterinario findByEmail(@Param("email") String email);

    @Query("select v from Veterinario v where  v.crmv = :crmv")
    public Veterinario findByCrmv(@Param("crmv") String crmv);

    @Query("select v from Veterinario v where  v.veterinarioId = :veterinarioId")
    public Veterinario findByVeterinarioId(@Param("veterinarioId") Integer veterinarioId);
}
