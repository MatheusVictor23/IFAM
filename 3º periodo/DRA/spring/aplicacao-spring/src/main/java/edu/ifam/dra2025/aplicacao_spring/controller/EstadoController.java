package edu.ifam.dra2025.aplicacao_spring.controller;

import edu.ifam.dra2025.aplicacao_spring.model.Estado;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    private List<Estado> estados = new ArrayList<>();

    private void carregarEstados() {
        estados.add(new Estado(1L, "Amazonas", "AM"));
        estados.add(new Estado(2L, "Pará", "PA"));
        estados.add(new Estado(3L, "São Paulo", "SP"));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> list() {
        carregarEstados();

        return estados;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado getById(@PathVariable int id) {
        return estados.get(id - 1);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Estado create(@RequestBody Estado estado) {
        estados.add(estado);
        return estado;
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable int id) {
        estados.remove(id - 1);
        return "Estado removido com sucesso!";
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public Estado update(@PathVariable int id, @RequestBody Estado estado) {
        estados.set(id - 1, estado);
        return estado;
    }


}
