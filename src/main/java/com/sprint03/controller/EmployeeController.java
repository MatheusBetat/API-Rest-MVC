package com.sprint03.controller;

import com.sprint03.service.funcionario.FuncionarioService;
import com.sprint03.model.mapper.request.FuncionarioRequest;
import com.sprint03.model.mapper.response.FuncionarioResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping({"/v1/funcionario"})
public class FuncionarioController {

    private final FuncionarioService service;


    @GetMapping
    public List<FuncionarioResponse> listarTodosFuncionarios(){
        return this.service.listarFuncionarios();
    }

    @GetMapping("/{id}")
    public FuncionarioResponse obterFuncionaroPorId(@PathVariable String id){
        return this.service.buscaFuncionarioId(id);
    }

    @PostMapping("/cadastro")
    public FuncionarioResponse cadastrarFuncionario(@RequestBody @Valid FuncionarioRequest funcionario) {
        return this.service.criaFuncionario(funcionario);
    }

    @PutMapping("/alterar/{id}")
    public FuncionarioResponse alterarFuncionarioId(@RequestBody FuncionarioRequest funcionario ,
                                                    @PathVariable String id){
        return this.service.alterarFuncionarioId(funcionario, id);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFuncionarioPeloId(@PathVariable String id){
        this.service.deletarFuncionarioPeloId(id);
    }

    @DeleteMapping("/deletar")
    public void deletarFuncionariosPelosIds(@RequestParam List<String> ids){
        service.deletarFuncionariosPelosIds(ids);
    }

}

