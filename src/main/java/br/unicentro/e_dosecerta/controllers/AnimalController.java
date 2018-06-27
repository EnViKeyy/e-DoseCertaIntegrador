package br.unicentro.e_dosecerta.controllers;

import br.unicentro.e_dosecerta.models.Animal;
import br.unicentro.e_dosecerta.models.Especie;
import br.unicentro.e_dosecerta.repository.AnimalRepository;
import br.unicentro.e_dosecerta.repository.EspecieRepository;
import br.unicentro.e_dosecerta.util.AnimalEspecie;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AnimalController {

    @Autowired
    private AnimalRepository animalRpt;

    @Autowired
    private EspecieRepository especieRpt;

    @RequestMapping(value = "/cadastro/animal", method = RequestMethod.GET)
    private ModelAndView cadastroAnimal() {
        ModelAndView mv = new ModelAndView("cadastro/animal");
        Iterable<Especie> especies = especieRpt.findAll();
        mv.addObject("especies", especies);
        return mv;
    }

    @RequestMapping(value = "/cadastro/animal", method = RequestMethod.POST)
    private String cadastroAnimal(@Valid Animal animal, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Campos obrigatórios não preenchidos!");
            return "redirect:/cadastro/animal";
        }
        animalRpt.save(animal);
        attributes.addFlashAttribute("mensagem", "Cadastro efetuado com sucesso!");
        return "redirect:/cadastro/animal";
    }

    @RequestMapping(value = "/consulta/animal", method = RequestMethod.GET)
    private ModelAndView consultaAnimal() {
        ModelAndView mv = new ModelAndView("consulta/animal");
        AnimalEspecie animalEsp;

        Iterable<Especie> especies = especieRpt.findAll();
        Iterable<Animal> animais = animalRpt.findAll();
        List<AnimalEspecie> animalEsps = new ArrayList<>();
        
        for(Animal animal : animais) {
            animalEsp = new AnimalEspecie();
            animalEsp.setNome(animal.getNome());
            animalEsp.setRg(animal.getRg());
            for(Especie especie : especies) {
                if(especie.getEspecieId() == animal.getEspecieId())
                     animalEsp.setEspecie(especie.getNome());
            }
        animalEsps.add(animalEsp);
        }
        
        mv.addObject("animalEsps", animalEsps);
        return mv;
    }
}
