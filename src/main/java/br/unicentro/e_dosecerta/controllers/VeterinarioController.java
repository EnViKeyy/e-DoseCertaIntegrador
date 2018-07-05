package br.unicentro.e_dosecerta.controllers;

import br.unicentro.e_dosecerta.models.Veterinario;
import br.unicentro.e_dosecerta.repository.VeterinarioRepository;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VeterinarioController {

    @Autowired
    private VeterinarioRepository veterinarioRpt;

    public Veterinario veterinarioAutenticado() {
        Veterinario veterinario = new Veterinario();
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                veterinario = ((Veterinario) authentication.getPrincipal());
            }
        }
        return veterinario;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String form() {
        return "index";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastroVeterinario(@Valid Veterinario veterinario, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("falha", "Campos obrigatórios não preenchidos!");
            return "redirect:/";
        }

        Veterinario veterinarioFind = veterinarioRpt.findByCrmv(veterinario.getCrmv());
        if (veterinarioFind != null) {
            if (!Objects.equals(veterinarioFind.getVeterinarioId(), veterinario.getVeterinarioId())) {
                attributes.addFlashAttribute("falha", "Este CRMV já foi cadastrado!");
                return "redirect:/";
            }
        }

        veterinarioFind = veterinarioRpt.findByEmail(veterinario.getEmail());
        if (veterinarioFind != null) {
            if (!Objects.equals(veterinarioFind.getVeterinarioId(), veterinario.getVeterinarioId())) {
                attributes.addFlashAttribute("falha", "Este email já foi cadastrado!");
                return "redirect:/";
            }
        }

        BCryptPasswordEncoder senhaCripto = new BCryptPasswordEncoder();
        String senha = senhaCripto.encode(veterinario.getSenha());
        veterinario.setSenha(senha);
        veterinarioRpt.save(veterinario);
        attributes.addFlashAttribute("sucesso", "Cadastro efetuado com sucesso!");
        return "redirect:/";
    }

    @RequestMapping(value = "/perfil", method = RequestMethod.GET)
    public ModelAndView perfil() {
        ModelAndView mv = new ModelAndView("/perfil");
        Veterinario veterinario = veterinarioAutenticado();
        veterinario = veterinarioRpt.findByVeterinarioId(veterinario.getVeterinarioId());
        mv.addObject("veterinario", veterinario);
        return mv;
    }

    @RequestMapping(value = "/alterar/perfil", method = RequestMethod.GET)
    public ModelAndView alterarPerfil() {
        ModelAndView mv = new ModelAndView("alterar/perfil");
        Veterinario veterinario = veterinarioAutenticado();
        veterinario = veterinarioRpt.findByVeterinarioId(veterinario.getVeterinarioId());
        mv.addObject("veterinario", veterinario);
        return mv;
    }

    @RequestMapping(value = "/alterar/perfil", method = RequestMethod.POST)
    public String alterarPerfil(@Valid Veterinario veterinario, BindingResult result, RedirectAttributes attributes) {

        Veterinario veterinarioFind = veterinarioRpt.findByCrmv(veterinario.getCrmv());
        if (veterinarioFind != null) {
            if (!Objects.equals(veterinarioFind.getVeterinarioId(), veterinario.getVeterinarioId())) {
                attributes.addFlashAttribute("falha", "Este CRMV já foi cadastrado!");
                return "redirect:/alterar/perfil";
            }
        }

        veterinarioFind = veterinarioRpt.findByEmail(veterinario.getEmail());
        if (veterinarioFind != null) {
            veterinarioFind = veterinarioRpt.findByEmail(veterinario.getEmail());
            if (!Objects.equals(veterinarioFind.getVeterinarioId(), veterinario.getVeterinarioId())) {
                attributes.addFlashAttribute("falha", "Este email já foi cadastrado!");
                return "redirect:/alterar/perfil";
            }
        }

        if (veterinario.getNome().isEmpty()) {
            veterinario.setNome(veterinarioFind.getNome());
        }

        if (veterinario.getCrmv().isEmpty()) {
            veterinario.setCrmv(veterinarioFind.getCrmv());
        }

        if (veterinario.getSenha().isEmpty()) {
            veterinario.setSenha(veterinarioFind.getSenha());
        } else {
            BCryptPasswordEncoder senhaCripto = new BCryptPasswordEncoder();
            String senha = senhaCripto.encode(veterinario.getSenha());
            veterinario.setSenha(senha);
        }

        if (veterinario.getEmail().isEmpty()) {
            veterinario.setEmail(veterinarioFind.getEmail());
        }

        veterinarioRpt.save(veterinario);
        attributes.addFlashAttribute("sucesso", "Perfil alterado com sucesso!");
        return "redirect:/perfil";
    }
}
