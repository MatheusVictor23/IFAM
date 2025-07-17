package com.example.demo.controller;

import com.example.demo.DTO.VoluntarioInputDTO;
import com.example.demo.DTO.VoluntarioOutputDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Avaliacao;
import com.example.demo.model.EstadoCivil;
import com.example.demo.model.Status;
import com.example.demo.model.Voluntario;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.repository.EstadoCivilRepository;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/voluntarios")
public class VoluntarioController {
    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    StatusRepository statusRepository;

//    @Autowired
//    EstadoCivilRepository estadoCivilRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoluntarioOutputDTO> getVoluntarios() {
        List<Voluntario> voluntarios = voluntarioRepository.findAll();

        if(voluntarios == null){
            throw new NotFoundException("Nenhum voluntário cadastrado!");
        }

        return voluntarios.stream()
                .map(v -> new VoluntarioOutputDTO(
                        v.getId(),
                        v.getPassaporte(),
                        v.getCPF(),
                        v.getNome() + " " + v.getSobrenome(),
                        v.getDataNascimento(),
                        v.getIdade(),
                        v.getTelefone(),
                        v.getEmail(),
                        v.getTipoSanguineo(),
                        v.getProfissao(),
                        v.getAnosExperiencia(),
                        v.getSituacaoSaude().getAvaliacao(),
                        v.getStatus().getStatus()
//                        v.getEstadoCivil().getEstadoCivil()
                ))
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VoluntarioOutputDTO addVoluntario(@RequestBody VoluntarioInputDTO voluntarioInputDTO) {

        if(voluntarioRepository.existsByCPF(voluntarioInputDTO.getCpf())){
            throw new BadRequestException("Esse CPF já está cadastrado!");
        }

        if(voluntarioRepository.existsByPassaporte(voluntarioInputDTO.getPassaporte()) ){
            throw new BadRequestException("Esse passaporte já está cadastrado!");
        }

        if(voluntarioRepository.existsByEmail(voluntarioInputDTO.getEmail()) ){
            throw new BadRequestException("Esse Email já está cadastrado!");
        }

        if(voluntarioInputDTO.getAnos_experiencia() < 3){
            throw new BadRequestException("É necessário ter ao menos 3 anos de experiência na profissão");
        }



//       EstadoCivil estadoCivil = estadoCivilRepository.findByEstadoCivil(voluntarioInputDTO.getEstadoCivil());



//        if(estadoCivil == null){
//            throw new BadRequestException("Estado civil inválido!");
//        }

        Voluntario voluntario = new Voluntario();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento = LocalDate.parse(voluntarioInputDTO.getData_nascimento(), formatter);

        Avaliacao avaliacao = avaliacaoRepository.findByAvaliacao(voluntarioInputDTO.getSituacao_saude());

        if(avaliacao == null){
            throw new NotFoundException("Avaliação não encontrada!");
        }

        Status status = statusRepository.findByStatus(voluntarioInputDTO.getEstado());

        if (status == null) {
            throw new NotFoundException("Status não encontrado!");
        }

        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

        voluntario.setPassaporte(voluntarioInputDTO.getPassaporte());
        voluntario.setCPF(voluntarioInputDTO.getCpf());
        voluntario.setNome(voluntarioInputDTO.getNome());
        voluntario.setSobrenome(voluntarioInputDTO.getSobrenome());
        voluntario.setDataNascimento(dataNascimento);
        voluntario.setIdade(idade);
        voluntario.setTelefone(voluntarioInputDTO.getTelefone());
        voluntario.setEmail(voluntarioInputDTO.getEmail());
        voluntario.setTipoSanguineo(voluntarioInputDTO.getTipo_sanguineo());
        voluntario.setProfissao(voluntarioInputDTO.getProfissao());
        voluntario.setAnosExperiencia(voluntarioInputDTO.getAnos_experiencia());
        voluntario.setSituacaoSaude(avaliacao);
        voluntario.setStatus(status);
//        voluntario.setEstadoCivil(estadoCivil);

        voluntarioRepository.save(voluntario);

        VoluntarioOutputDTO voluntarioOutputDTO = new VoluntarioOutputDTO(
                voluntario.getId(),
                voluntario.getPassaporte(),
                voluntario.getCPF(),
                voluntario.getNome() + " " + voluntario.getSobrenome(),
                voluntario.getDataNascimento(),
                voluntario.getIdade(),
                voluntario.getTelefone(),
                voluntario.getEmail(),
                voluntario.getTipoSanguineo(),
                voluntario.getProfissao(),
                voluntario.getAnosExperiencia(),
                voluntario.getSituacaoSaude().getAvaliacao(),
                voluntario.getStatus().getStatus()
//                voluntario.getEstadoCivil().getEstadoCivil()
        );

        return voluntarioOutputDTO;
        
        
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VoluntarioOutputDTO getVoluntarioByCpf(@PathVariable Long id) {
        Voluntario voluntario = voluntarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Voluntário não encontrado"));

        VoluntarioOutputDTO voluntarioOutputDTO = new VoluntarioOutputDTO(
                id,
                voluntario.getPassaporte(),
                voluntario.getCPF(),
                voluntario.getNome() + " " + voluntario.getSobrenome(),
                voluntario.getDataNascimento(),
                voluntario.getIdade(),
                voluntario.getTelefone(),
                voluntario.getEmail(),
                voluntario.getTipoSanguineo(),
                voluntario.getProfissao(),
                voluntario.getAnosExperiencia(),
                voluntario.getSituacaoSaude().getAvaliacao(),
                voluntario.getStatus().getStatus()
//                voluntario.getEstadoCivil().getEstadoCivil()
        );

        return voluntarioOutputDTO;
    }



    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VoluntarioOutputDTO updateVoluntario(@PathVariable Long id, @RequestBody VoluntarioInputDTO voluntarioInputDTO) {
        Voluntario voluntario = voluntarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Voluntário não encontrado"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento = LocalDate.parse(voluntarioInputDTO.getData_nascimento(), formatter);

        Avaliacao avaliacao = avaliacaoRepository.findByAvaliacao(voluntarioInputDTO.getSituacao_saude());

        if(avaliacao == null){
            throw new NotFoundException("Avaliação não encontrada!");
        }

        Status status = statusRepository.findByStatus(voluntarioInputDTO.getEstado());

        if (status == null) {
            throw new NotFoundException("Status não encontrado!");
        }


//        EstadoCivil estadoCivil = estadoCivilRepository.findByEstadoCivil(voluntarioInputDTO.getEstadoCivil());



//        if(estadoCivil == null){
//            throw new BadRequestException("Estado civil inválido!");
//        }


        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

        voluntario.setPassaporte(voluntarioInputDTO.getPassaporte());
        voluntario.setNome(voluntarioInputDTO.getNome());
        voluntario.setSobrenome(voluntarioInputDTO.getSobrenome());
        voluntario.setDataNascimento(dataNascimento);
        voluntario.setIdade(idade);
        voluntario.setTelefone(voluntarioInputDTO.getTelefone());
        voluntario.setEmail(voluntarioInputDTO.getEmail());
        voluntario.setTipoSanguineo(voluntarioInputDTO.getTipo_sanguineo());
        voluntario.setProfissao(voluntarioInputDTO.getProfissao());
        voluntario.setAnosExperiencia(voluntarioInputDTO.getAnos_experiencia());
        voluntario.setSituacaoSaude(avaliacao);
        voluntario.setStatus(status);
//        voluntario.setEstadoCivil(estadoCivil);

        voluntarioRepository.save(voluntario);

        VoluntarioOutputDTO voluntarioOutputDTO = new VoluntarioOutputDTO(
                id,
                voluntario.getPassaporte(),
                voluntario.getCPF(),
                voluntario.getNome() + " " + voluntario.getSobrenome(),
                voluntario.getDataNascimento(),
                voluntario.getIdade(),
                voluntario.getTelefone(),
                voluntario.getEmail(),
                voluntario.getTipoSanguineo(),
                voluntario.getProfissao(),
                voluntario.getAnosExperiencia(),
                voluntario.getSituacaoSaude().getAvaliacao(),
                voluntario.getStatus().getStatus()
//                voluntario.getEstadoCivil().getEstadoCivil()
        );

        return voluntarioOutputDTO;
    }




    @DeleteMapping(value = "/{id}")
    public String deleteVoluntario(@PathVariable Long id) {
        Voluntario voluntario = voluntarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Voluntário não encontrado"));

        voluntarioRepository.delete(voluntario);
        return "Voluntário deletado com sucesso";
    }

}
