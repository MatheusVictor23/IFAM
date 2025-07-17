package com.example.demo.controller;


import com.example.demo.DTO.MissaoInputDTO;
import com.example.demo.DTO.MissaoOutputDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Missao;
import com.example.demo.model.Pais;
import com.example.demo.repository.MissaoRepository;
import com.example.demo.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/missoes")
public class MissaoController {
    @Autowired
    private MissaoRepository missaoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MissaoOutputDTO> getMissoes() {
        List<Missao> missoes = missaoRepository.findAll();

        if(missoes == null){
            throw new BadRequestException("Nenhuma missão cadastrada!");
        }

        return missoes.stream().map(m -> new MissaoOutputDTO(
                m.getNome(),
                m.getDescricao(),
                m.getPais().getNome()
        )).collect(Collectors.toList());

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MissaoOutputDTO getMissaoByNome(@PathVariable Long id) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Missão não encontrada!"));

        return new MissaoOutputDTO(missao.getNome(), missao.getDescricao(), missao.getPais().getNome());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MissaoOutputDTO addMissao(@RequestBody MissaoInputDTO missaoInputDTO) {
        Missao missao = new Missao();

        Pais pais = paisRepository.findByNome(missaoInputDTO.getPais());

        if (pais == null) {
            throw new NotFoundException("País não encontrado!");
        }

        missao.setNome(missaoInputDTO.getNome());
        missao.setDescricao(missaoInputDTO.getDescricao());
        missao.setPais(pais);

        missaoRepository.save(missao);

        return new MissaoOutputDTO(missao.getNome(), missao.getDescricao(), pais.getNome());
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MissaoOutputDTO updateMissao(@PathVariable Long id, @RequestBody MissaoInputDTO missaoInputDTO) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Missão não encontrada!"));

        Pais pais = paisRepository.findByNome(missaoInputDTO.getPais());
        if (pais == null) {
            throw new NotFoundException("País não encontrado!");
        }

        missao.setDescricao(missaoInputDTO.getDescricao());
        missao.setPais(pais);

        missaoRepository.save(missao);

        return new MissaoOutputDTO(missao.getNome(), missao.getDescricao(), pais.getNome());
    }

    @DeleteMapping(value = "/{id}")
    public String deleteMissao(@PathVariable Long id) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Missão não encontrada!"));

        missaoRepository.delete(missao);
        return "Missão deletada com sucesso!";
    }


}
