package edu.ifam.dra2025.aplicacao_spring.controller;

import edu.ifam.dra2025.aplicacao_spring.dto.CidadeInputDto;
import edu.ifam.dra2025.aplicacao_spring.model.Cidade;
import edu.ifam.dra2025.aplicacao_spring.model.Estado;
import edu.ifam.dra2025.aplicacao_spring.repository.CidadeRepository;
import edu.ifam.dra2025.aplicacao_spring.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;


    public CidadeController() { }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade create(@RequestBody CidadeInputDto cidadeInputDto){

        return cidadeRepository.save(cidadeInputDto.build(estadoRepository));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cidade> list(){
            return cidadeRepository.findAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade getById(@PathVariable Long id){
        return cidadeRepository.findById(id).get();
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(@PathVariable Long id){
        try {
            cidadeRepository.deleteById(id);
            return "Deletado com sucesso!";
        }catch(Exception e){
            return "Erro ao deletar:"+e.getMessage();
        }

    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade updade(@PathVariable Long id,@RequestBody Cidade cidade){
        cidade.setId(id);
        return cidadeRepository.save(cidade);
    }



}
