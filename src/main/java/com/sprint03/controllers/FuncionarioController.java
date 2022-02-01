package com.sprint03.controllers;

import com.sprint03.entities.Funcionario;
import com.sprint03.repositories.FuncionarioRepository;
import com.sprint03.services.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/funcionario"})
public class FuncionarioController {

    private final FuncionarioService service;

    private final FuncionarioRepository repository;

    public FuncionarioController(final FuncionarioService service, final FuncionarioRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Funcionario> listarTodosFuncionarios(){
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> obterFuncionaroPorId(@PathVariable String id) {
        return this.service.buscaFuncionarioId(id);
    }

    @PostMapping({"/cadastro"})
    public Funcionario cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        return this.service.criaFuncionario(funcionario);
    }

    @PutMapping({"/alterar/{id}"})
    public ResponseEntity<Funcionario> alterarFuncionarioPeloId(@PathVariable("id") String id,
                                                                @RequestBody Funcionario funcionario) {
        return repository.findById(id)
                .map(f -> {
                    f.setNome(funcionario.getNome());
                    Funcionario alterado = repository.save(f);
                    return ResponseEntity.ok().body(alterado);
                }).orElse(ResponseEntity.notFound().build());
       }

    @DeleteMapping("/deletar/{id}")
    public void deletarFuncionarioPeloId(@PathVariable String id){
        this.service.deletarFuncionarioPeloId(id);
    }

}

