package com.example.demo.controller;


import com.example.demo.DTO.VoluntarioMissaoInputDTO;
import com.example.demo.DTO.VoluntarioMissaoOutputDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/voluntario_missao")
public class VoluntarioMissaoController {
    @Autowired
    private VoluntarioMissaoRepository voluntarioMissaoRepository;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private MissaoRepository missaoRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoluntarioMissaoOutputDTO> getVoluntarioMissao(@PathVariable Long id) {

        Voluntario voluntario = voluntarioRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Voluntário não encontrado!"));


        List<VoluntarioMissao> missoesVoluntario = voluntarioMissaoRepository.findAllByVoluntario(voluntario);

        if (missoesVoluntario.isEmpty()) {
            throw new NotFoundException("O voluntário não possui missões cadastradas.");
        }

        return missoesVoluntario.stream().map(v -> new VoluntarioMissaoOutputDTO(
                v.getVoluntario().getNome() + " " + v.getVoluntario().getSobrenome(),
                v.getMissao().getNome(),
                v.getCidadeAtuacao().getEstado().getNome(),
                v.getCidadeAtuacao().getNome(),
                v.getDataInicio(),
                v.getDataFim(),
                v.getAvaliacao().getAvaliacao(),
                v.getParecer()
        )).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VoluntarioMissaoOutputDTO addVoluntarioMissao(@RequestBody VoluntarioMissaoInputDTO voluntarioMissaoInputDTO) {
        VoluntarioMissao voluntarioMissao = new VoluntarioMissao();

        Voluntario voluntario = voluntarioRepository.findByNome(voluntarioMissaoInputDTO.getVoluntario());

        if(voluntario == null){
            throw new NotFoundException("Voluntário não encontrado!");
        }

        Missao missao = missaoRepository.findByNome(voluntarioMissaoInputDTO.getMissao());

        if(missao == null){
            throw new NotFoundException("Missão não encontrada!");
        }

        Avaliacao avaliacao = avaliacaoRepository.findByAvaliacao(voluntarioMissaoInputDTO.getAvaliacao());

        if(avaliacao == null){
            throw new NotFoundException("Avaliação não encontrada!");
        }

        Cidade cidade = cidadeRepository.findByNome(voluntarioMissaoInputDTO.getCidadeAtuacao());

        if (cidade == null) {
            throw new NotFoundException("Cidade não encontrada!");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dataInicio = LocalDate.parse(voluntarioMissaoInputDTO.getDataInicio(), formatter);

        LocalDate dataFim = LocalDate.parse(voluntarioMissaoInputDTO.getDataFim(), formatter);

        voluntarioMissao.setVoluntario(voluntario);
        voluntarioMissao.setMissao(missao);
        voluntarioMissao.setCidadeAtuacao(cidade);
        voluntarioMissao.setDataInicio(dataInicio);
        voluntarioMissao.setDataFim(dataFim);
        voluntarioMissao.setAvaliacao(avaliacao);
        voluntarioMissao.setParecer(voluntarioMissaoInputDTO.getParecer());

        if(!cidade.getEstado().getPais().equals(missao.getPais())){
            throw new BadRequestException("A cidade de atuação não pertence ao mesmo país da missão.");
        }

        voluntarioMissaoRepository.save(voluntarioMissao);

        return new VoluntarioMissaoOutputDTO(
                voluntarioMissao.getVoluntario().getNome() + " " + voluntarioMissao.getVoluntario().getSobrenome(),
                voluntarioMissao.getMissao().getNome(),
                voluntarioMissao.getCidadeAtuacao().getEstado().getNome(),
                voluntarioMissao.getCidadeAtuacao().getNome(),
                voluntarioMissao.getDataInicio(),
                voluntarioMissao.getDataFim(),
                voluntarioMissao.getAvaliacao().getAvaliacao(),
                voluntarioMissao.getParecer());
    }



    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VoluntarioMissaoOutputDTO updateVoluntarioMissao(@PathVariable Long id, @RequestBody VoluntarioMissaoInputDTO dto) {
        VoluntarioMissao voluntarioMissao = voluntarioMissaoRepository.findById(id).get();

        if (voluntarioMissao == null) {
            throw new RuntimeException("O voluntário ainda não participou de nenhuma missão!");
        }

        Voluntario voluntario = voluntarioRepository.findByNome(dto.getVoluntario());

        if(voluntario == null){
            throw new NotFoundException("Voluntário não encontrado!");
        }

        Missao missao = missaoRepository.findByNome(dto.getMissao());

        if(missao == null){
            throw new NotFoundException("Missão não encontrada!");
        }

        Avaliacao avaliacao = avaliacaoRepository.findByAvaliacao(dto.getAvaliacao());

        if(avaliacao == null){
            throw new NotFoundException("Avaliação não encontrada!");
        }


        Cidade cidade = cidadeRepository.findByNome(dto.getCidadeAtuacao());

        if (cidade == null) {
            throw new NotFoundException("Cidade não encontrada!");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInicio = LocalDate.parse(dto.getDataInicio(), formatter);
        LocalDate dataFim = LocalDate.parse(dto.getDataFim(), formatter);

        if (!cidade.getEstado().getPais().equals(missao.getPais())) {
            throw new BadRequestException("A cidade de atuação não pertence ao mesmo país da missão.");
        }

        voluntarioMissao.setVoluntario(voluntario);
        voluntarioMissao.setMissao(missao);
        voluntarioMissao.setCidadeAtuacao(cidade);
        voluntarioMissao.setDataInicio(dataInicio);
        voluntarioMissao.setDataFim(dataFim);
        voluntarioMissao.setAvaliacao(avaliacao);
        voluntarioMissao.setParecer(dto.getParecer());

        voluntarioMissaoRepository.save(voluntarioMissao);

        VoluntarioMissaoOutputDTO voluntarioMissaoOutputDTO = new VoluntarioMissaoOutputDTO(
                voluntarioMissao.getVoluntario().getNome() + " " + voluntarioMissao.getVoluntario().getSobrenome(),
                voluntarioMissao.getMissao().getNome(),
                voluntarioMissao.getCidadeAtuacao().getEstado().getNome(),
                voluntarioMissao.getCidadeAtuacao().getNome(),
                voluntarioMissao.getDataInicio(),
                voluntarioMissao.getDataFim(),
                voluntarioMissao.getAvaliacao().getAvaliacao(),
                voluntarioMissao.getParecer());

        return voluntarioMissaoOutputDTO;
    }



    @DeleteMapping("/{id}")
    public String deleteVoluntarioMissao(@PathVariable Long id) {
        VoluntarioMissao voluntarioMissao = voluntarioMissaoRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Voluntário Missão não encontrado!"));

        voluntarioMissaoRepository.deleteById(id);
        return "Missão removida com sucesso.";
    }

}
