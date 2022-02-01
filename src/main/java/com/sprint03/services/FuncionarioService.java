package com.sprint03.services;

import com.sprint03.entities.Funcionario;
import com.sprint03.repositories.FuncionarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    private FuncionarioRepository repository;

    public Funcionario criaFuncionario(Funcionario funcionario){
        return this.repository.save(funcionario);
    }

    public Optional<Funcionario> buscaFuncionarioId(String id){
        return this.repository.findById(id);
    }

    public void deletarFuncionarioPeloId(String id){
        repository.deleteById(id);
    }


}
