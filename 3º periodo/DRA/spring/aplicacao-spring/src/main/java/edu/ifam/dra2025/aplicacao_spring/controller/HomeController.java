package edu.ifam.dra2025.aplicacao_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String init(){
        return "Testando minha aplicação Spring";
    }

}
