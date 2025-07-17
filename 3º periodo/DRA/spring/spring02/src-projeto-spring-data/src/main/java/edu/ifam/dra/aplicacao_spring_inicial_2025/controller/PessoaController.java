package edu.ifam.dra.aplicacao_spring_inicial_2025.controller;


import edu.ifam.dra.aplicacao_spring_inicial_2025.model.Pessoa;
import edu.ifam.dra.aplicacao_spring_inicial_2025.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pessoa> list(){
        return pessoaRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa create(@RequestBody Pessoa pessoa){

        return pessoaRepository.save(pessoa);

    }
//    http://localhost:8080/api/pessoas/1
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa findById(@PathVariable Long id){
        return pessoaRepository.findById(id).get();
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(@PathVariable Long id){
        pessoaRepository.deleteById(id);

        return "Deletado com sucesso";

    }




}
