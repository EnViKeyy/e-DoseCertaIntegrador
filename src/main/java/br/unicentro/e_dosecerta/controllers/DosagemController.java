package br.unicentro.e_dosecerta.controllers;

import br.unicentro.e_dosecerta.models.Animal;
import br.unicentro.e_dosecerta.models.Dosagem;
import br.unicentro.e_dosecerta.models.Farmaco;
import br.unicentro.e_dosecerta.models.FarmacoEspecie;
import br.unicentro.e_dosecerta.models.FarmacoEspeciePK;
import br.unicentro.e_dosecerta.models.Veterinario;
import br.unicentro.e_dosecerta.repository.AnimalRepository;
import br.unicentro.e_dosecerta.repository.DosagemRepository;
import br.unicentro.e_dosecerta.repository.EspecieRepository;
import br.unicentro.e_dosecerta.repository.FarmacoEspecieRepository;
import br.unicentro.e_dosecerta.repository.FarmacoRepository;
import br.unicentro.e_dosecerta.util.DosagemFarmacoAnimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DosagemController {

    @Autowired
    private DosagemRepository dosagemRpt;

    @Autowired
    private AnimalRepository animalRpt;

    @Autowired
    private EspecieRepository especieRpt;

    @Autowired
    private FarmacoRepository farmacoRpt;

    @Autowired
    private FarmacoEspecieRepository farmacoEspRpt;

    @RequestMapping(value = "/cadastro/dosagem", method = RequestMethod.GET)
    private ModelAndView cadastroDosagem() {
        ModelAndView mv = new ModelAndView("cadastro/dosagem");
        Iterable<Animal> animais = animalRpt.findAll();
        mv.addObject("animais", animais);
        return mv;
    }

    @RequestMapping(value = "/cadastro/dosagem1", method = RequestMethod.GET)
    private ModelAndView cadastroDosagem2(Animal animal) {
        ModelAndView mv = new ModelAndView("cadastro/dosagem1");
        List<Farmaco> farmacos = new ArrayList<>();
        Farmaco farmaco;
        animal = animalRpt.findByAnimalId(animal.getAnimalId());

        Iterable<FarmacoEspecie> farmacoEsps = farmacoEspRpt.findByEspecieId(animal.getEspecieId());
        for (FarmacoEspecie farmacoEsp : farmacoEsps) {
            farmaco = farmacoRpt.findByFarmacoId(farmacoEsp.getFarmacoEspeciePK().getFarmacoId());
            farmacos.add(farmaco);
        }
        mv.addObject("farmacos", farmacos);
        return mv;
    }

    @RequestMapping(value = "/cadastro/dosagem1", method = RequestMethod.POST)
    private String cadastroDosagem1(Dosagem dosagem, Animal animal) {
        Farmaco farmaco = farmacoRpt.findByFarmacoId(dosagem.getFarmacoId());
        Float dose;
        
        dosagem.setAnimalId(animal.getAnimalId());
        dosagem.setData(new Date());
        dosagem.setVeterinarioId(new VeterinarioController().veterinarioAutenticado().getVeterinarioId());

        FarmacoEspecie farmacoEsp = farmacoEspRpt.findByFarmacoId(farmaco.getFarmacoId());
        dosagem.setData(new Date());
        dosagem.setVeterinarioId(new VeterinarioController().veterinarioAutenticado().getVeterinarioId());
        System.out.println(dosagem.getPeso());
        System.out.println(farmacoEsp.getDoseMaxima());
        System.out.println(farmaco.getConcentracao());
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

        return "redirect:/consulta/dosagem";
    }

    @RequestMapping(value = "/consulta/dosagem", method = RequestMethod.GET)
    private ModelAndView consultaDosagem() {
        ModelAndView mv = new ModelAndView("consulta/dosagem");

        DosagemFarmacoAnimal doseFarAnimal;
        List<DosagemFarmacoAnimal> doseFarAnimais = new ArrayList<>();

        Animal animal;
        List<Animal> animais = new ArrayList<>();

        Farmaco farmaco;
        List<Farmaco> farmacos = new ArrayList<>();

        Veterinario veterinario = new VeterinarioController().veterinarioAutenticado();
        Iterable<Dosagem> dosagens = dosagemRpt.findByVeterinarioId(veterinario.getVeterinarioId());

        for (Dosagem dosagem : dosagens) {
            doseFarAnimal = new DosagemFarmacoAnimal();

            doseFarAnimal.setData(dosagem.getData());
            doseFarAnimal.setDosagem(dosagem.getDosagem());
            doseFarAnimal.setPeso(dosagem.getPeso());

            animal = animalRpt.findByAnimalId(dosagem.getAnimalId());
            doseFarAnimal.setAnimal(animal.getNome());

            farmaco = farmacoRpt.findByFarmacoId(dosagem.getFarmacoId());
            doseFarAnimal.setFarmaco(farmaco.getNome());

            doseFarAnimais.add(doseFarAnimal);
        }
        mv.addObject("doseFarAnimais", doseFarAnimais);

        return mv;
    }
}
