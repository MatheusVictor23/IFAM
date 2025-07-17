package edu.ifam.dra2025.aplicacao_spring.controller;

import edu.ifam.dra2025.aplicacao_spring.model.Estado;
import edu.ifam.dra2025.aplicacao_spring.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado findById(@PathVariable Long id){
        return estadoRepository.findById(id).get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado save(@RequestBody Estado estado){
        return estadoRepository.save(estado);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable Long id){
        estadoRepository.deleteById(id);

        return "Estado deletado sucesso!";
    }
}
