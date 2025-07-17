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


    public EstadoController() { }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> list(){
            return estadoRepository.findAll();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado create(@RequestBody Estado estado){
        return estadoRepository.save(estado);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado getById(@PathVariable Long id){
        return estadoRepository.findById(id).get();
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(@PathVariable Long id){
        try {
            estadoRepository.deleteById(id);
            return "Deletado com sucesso!";
        }catch(Exception e){
            return "Erro ao deletar:"+e.getMessage();
        }

    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado updade(@PathVariable Long id,@RequestBody Estado estado){
        estado.setId(id);
        return estadoRepository.save(estado);
    }



}
