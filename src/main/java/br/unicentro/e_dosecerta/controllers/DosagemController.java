package br.unicentro.e_dosecerta.controllers;

import br.unicentro.e_dosecerta.models.Animal;
import br.unicentro.e_dosecerta.models.Dosagem;
import br.unicentro.e_dosecerta.models.Farmaco;
import br.unicentro.e_dosecerta.models.FarmacoEspecie;
import br.unicentro.e_dosecerta.models.FarmacoEspeciePK;
import br.unicentro.e_dosecerta.repository.AnimalRepository;
import br.unicentro.e_dosecerta.repository.DosagemRepository;
import br.unicentro.e_dosecerta.repository.FarmacoEspecieRepository;
import br.unicentro.e_dosecerta.repository.FarmacoRepository;
import br.unicentro.e_dosecerta.util.UserAuthenticated;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DosagemController {

    @Autowired
    private DosagemRepository dosagemRpt;

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

        return mv;
    }
    
    @RequestMapping(value = "/cadastro/dosagem", method = RequestMethod.POST)
    private String form(Dosagem dosagem) {
        Animal animal = animalRpt.findByAnimalId(dosagem.getAnimalId());
        Farmaco farmaco = farmacoRpt.findByFarmacoId(dosagem.getFarmacoId());
        UserAuthenticated userAuth = new UserAuthenticated();

        FarmacoEspeciePK farmacoEspPK = new FarmacoEspeciePK();
        farmacoEspPK.setEspecieId(animal.getEspecieId());
        farmacoEspPK.setFarmacoId(dosagem.getFarmacoId());
        FarmacoEspecie farmacoEsp = farmacoEspRpt.findByEspecieFarmacoId(farmacoEspPK);

        dosagem.setData(new Date());
        dosagem.setVeterinarioId(userAuth.getVeterinario().getVeterinarioId());

        Float dose;

        dose = (dosagem.getPeso() * farmacoEsp.getDoseMaxima()) / farmaco.getConcentracao();
        dosagem.setDosagem(dose);
        dosagemRpt.save(dosagem);

        Dosagem dose2 = new Dosagem();

        dose2.setAnimalId(dosagem.getAnimalId());
        dose2.setData(new Date());
        dose2.setDosagem(0);
        dose = (dosagem.getPeso() * farmacoEsp.getDoseMinima()) / farmaco.getConcentracao();
        dose2.setDosagem(dose);
        dose2.setDosagemId(dosagem.getDosagemId() + 1);
        dose2.setFarmacoId(dosagem.getFarmacoId());
        dose2.setPeso(dosagem.getPeso());
        dose2.setVeterinarioId(dosagem.getVeterinarioId());
        dosagemRpt.save(dose2);

        dose2 = new Dosagem();
        dose2.setAnimalId(dosagem.getAnimalId());
        dose2.setData(new Date());
        dose2.setDosagem(0);
        dose = (dosagem.getPeso() * farmacoEsp.getDoseMedia()) / farmaco.getConcentracao();
        dose2.setDosagem(dose);
        dose2.setDosagemId(dosagem.getDosagemId() + 2);
        dose2.setFarmacoId(dosagem.getFarmacoId());
        dose2.setPeso(dosagem.getPeso());
        dose2.setVeterinarioId(dosagem.getVeterinarioId());
        dosagemRpt.save(dose2);

        return "redirect:/cadastro/dosagem";
    }
}
