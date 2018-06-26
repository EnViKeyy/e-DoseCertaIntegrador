package br.unicentro.e_dosecerta.security;

import br.unicentro.e_dosecerta.models.Veterinario;
import br.unicentro.e_dosecerta.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private VeterinarioRepository veterinarioRpt;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Veterinario veterinario = veterinarioRpt.findByEmail(email);

        if (veterinario == null) {
            throw new UsernameNotFoundException("Veterinario não encontrado!");
        }
        return veterinario;
    }
}
