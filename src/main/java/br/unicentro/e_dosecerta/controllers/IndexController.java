package br.unicentro.e_dosecerta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/sobre")
    public String sobre() {
        return "about";
    }

    @RequestMapping("/contato")
    public String contato() {
        return "contact";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
