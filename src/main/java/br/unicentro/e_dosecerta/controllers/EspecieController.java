package br.unicentro.e_dosecerta.controllers;

import br.unicentro.e_dosecerta.models.Especie;
import br.unicentro.e_dosecerta.repository.EspecieRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EspecieController {

    @Autowired
    private EspecieRepository er;

    @RequestMapping(value = "/especie", method = RequestMethod.GET)
    public String form() {
        List<Especie> especies = new ArrayList<>();
        er.findAll();
//        Lists.newArrayList(er.findAll());
        return "especie/formEspecie";
    }
    
    @RequestMapping("/especies")
    public ModelAndView especieList() {
        ModelAndView mv = new ModelAndView("especie/formEspecie");
        Iterable<Especie> especies = er.findAll();
        mv.addObject("especies", especies);
        return mv;
    }
}
