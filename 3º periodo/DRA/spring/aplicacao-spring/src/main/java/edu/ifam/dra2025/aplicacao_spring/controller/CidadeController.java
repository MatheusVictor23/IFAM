package edu.ifam.dra2025.aplicacao_spring.controller;

import edu.ifam.dra2025.aplicacao_spring.model.Cidade;
import edu.ifam.dra2025.aplicacao_spring.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cidade> getCidades() {
        return cidadeRepository.findAll();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade getCidade(@PathVariable Long id) {
        return cidadeRepository.findById(id).get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade save(@RequestBody Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCidade(@PathVariable Long id) {
        cidadeRepository.deleteById(id);

        return "Cidade deletado com sucesso!";
    }
}
