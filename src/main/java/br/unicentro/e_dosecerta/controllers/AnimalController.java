package br.unicentro.e_dosecerta.controllers;

import br.unicentro.e_dosecerta.models.Animal;
import br.unicentro.e_dosecerta.models.Especie;
import br.unicentro.e_dosecerta.repository.AnimalRepository;
import br.unicentro.e_dosecerta.repository.EspecieRepository;
import br.unicentro.e_dosecerta.util.AnimalEspecie;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

        Animal animalFind = animalRpt.findByRg(animal.getRg());
        if (animalFind != null) {
            if (!Objects.equals(animalFind.getAnimalId(), animal.getAnimalId())) {
                attributes.addFlashAttribute("falha", "Este RG já foi cadastrado!");
                return "redirect:/cadastro/animal/";
            }
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

        for (Animal animal : animais) {
            animalEsp = new AnimalEspecie();
            animalEsp.setAnimalId(animal.getAnimalId());
            animalEsp.setNome(animal.getNome());
            animalEsp.setRg(animal.getRg());
            for (Especie especie : especies) {
                if (especie.getEspecieId() == animal.getEspecieId()) {
                    animalEsp.setEspecie(especie.getNome());
                }
            }
            animalEsps.add(animalEsp);
        }

        mv.addObject("animalEsps", animalEsps);
        return mv;
    }

    @RequestMapping(value = "animal/{rg}", method = RequestMethod.GET)
    public ModelAndView detalhesAnimal(@PathVariable("rg") String rg) {
        Animal animal = animalRpt.findByRg(rg);
        ModelAndView mv = new ModelAndView("alterar/animal");

        Iterable<Especie> especies = especieRpt.findAll();

        mv.addObject("especies", especies);
        mv.addObject("animal", animal);

        return mv;
    }

    @RequestMapping(value = "/animal/{rg}", method = RequestMethod.POST)
    public String alterarAnimal(@RequestParam("rg") String rg, Animal animal, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("falha", "Campos obrigatórios não preenchidos!");
            return "redirect:/animal/" + rg;
        }

        Animal animalFind = animalRpt.findByRg(animal.getRg());
        if (animalFind != null) {
            if (!Objects.equals(animalFind.getAnimalId(), animal.getAnimalId())) {
                attributes.addFlashAttribute("falha", "Este RG já foi cadastrado!");
                return "redirect:/animal/" + rg;
            }
        }
        animalRpt.save(animal);
        attributes.addFlashAttribute("sucesso", "Cadastro efetuado com sucesso!");
        return "redirect:/animal/" + rg;
    }
}
