package br.unicentro.e_dosecerta.util;

import br.unicentro.e_dosecerta.models.Veterinario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuthenticated {

    private Veterinario veterinario;

    public UserAuthenticated() {
        veterinario = new Veterinario();
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                veterinario.setVeterinarioId(((Veterinario) authentication.getPrincipal()).getVeterinarioId());
            }
        }
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}
