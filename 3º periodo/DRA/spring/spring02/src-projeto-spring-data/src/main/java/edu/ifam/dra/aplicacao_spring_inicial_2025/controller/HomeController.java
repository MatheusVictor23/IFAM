package edu.ifam.dra.aplicacao_spring_inicial_2025.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String iniciar(){
        return "Testando projeto SpringBoot!";
    }

}
