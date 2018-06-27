package br.unicentro.e_dosecerta.controllers;

import br.unicentro.e_dosecerta.models.Animal;
import br.unicentro.e_dosecerta.models.Dosagem;
import br.unicentro.e_dosecerta.models.Farmaco;
import br.unicentro.e_dosecerta.models.FarmacoEspecie;
import br.unicentro.e_dosecerta.repository.AnimalRepository;
import br.unicentro.e_dosecerta.repository.DosagemRepository;
import br.unicentro.e_dosecerta.repository.FarmacoEspecieRepository;
import br.unicentro.e_dosecerta.repository.FarmacoRepository;
import static java.lang.System.console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DosagemController {

    @Autowired
    private DosagemRepository dosagemRpt;

//    @Autowired
//    private VeterinarioRepository vr;

    @Autowired
    private AnimalRepository animalRpt;

    @Autowired
    private FarmacoRepository farmacoRpt;

    @Autowired
    private FarmacoEspecieRepository farmacoEspRpt;

    @RequestMapping(value = "/cadastro/dosagem", method = RequestMethod.GET)
    private ModelAndView animais() {
        ModelAndView mv = new ModelAndView("Cadastro/dosagem");

        Iterable<Animal> animais = animalRpt.findAll();
        mv.addObject("animais", animais);

        Iterable<Farmaco> farmacos = farmacoRpt.findAll();
        mv.addObject("farmacos", farmacos);
//        Iterable<Veterinario> vets = vr.findAll();
//        mv.addObject("vets", vets);
//        Iterable<FarmacoEspecie> farmacosEspecies = farmacoEspRpt.findAll();
//        mv.addObject("farmacosEspecies", farmacosEspecies);

        return mv;
    }

//    @RequestMapping(value = "/cadastro/dosagem", method = RequestMethod.GET)
//    private ModelAndView farmacos(@PathVariable("animalId") Integer animalId) {
//        ModelAndView mv = new ModelAndView("cadastro/dosagem");
//        Animal animal = animalRpt.findByAnimalId(animalId);
//
//        console();
//        
//        System.out.println(animal);
//        System.out.println(animal.getEspecieId());
//        System.out.println(animal.getEspecieId());
//        Iterable<FarmacoEspecie> farmacosEsp = farmacoEspRpt.findByEspecieId(animal.getEspecieId());
//        mv.addObject("farmacosEsp", farmacosEsp);
//        
//        Iterable<Farmaco> farmacos = farmacoRpt.findByfarmacoId(farmacosEsp);
//        mv.addObject("farmacos", farmacos);
//
//        return mv;
//    }

    @RequestMapping(value = "/cadastro/dosagem", method = RequestMethod.POST)
    private String form(Dosagem dosagem) {
        dosagemRpt.save(dosagem);
        return "redirect:/cadastro/dosagem";
    }
}
