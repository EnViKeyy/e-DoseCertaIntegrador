package br.unicentro.e_dosecerta.controllers;

import br.unicentro.e_dosecerta.models.Veterinario;
import br.unicentro.e_dosecerta.repository.VeterinarioRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VeterinarioController {

    @Autowired
    private VeterinarioRepository veterinarioRpt;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String form() {
        return "index";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastroVeterinario(@Valid Veterinario veterinario, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Campos obrigatórios não preenchidos!");
            return "redirect:/index";
        }
        BCryptPasswordEncoder senhaCripto = new BCryptPasswordEncoder();
        String senha = senhaCripto.encode(veterinario.getSenha());
        veterinario.setSenha(senha);
        veterinarioRpt.save(veterinario);
        attributes.addFlashAttribute("mensagem", "Cadastro efetuado com sucesso!");
        return "redirect:/index";
    }
}
