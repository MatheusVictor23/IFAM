package edu.ifam.dra2025.aplicacao_spring.controller;

import edu.ifam.dra2025.aplicacao_spring.model.Pessoa;
import edu.ifam.dra2025.aplicacao_spring.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pessoa> list() {
        return pessoaRepository.findAll();
    }

    @GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa findById(@PathVariable Long id) {
        return pessoaRepository.findById(id).get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa save(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
        return "Deletado com sucesso!";
    }

}
