package com.sprint03.service.funcionario;

import com.sprint03.exceptions.notfound.NotFoundException;
import com.sprint03.model.entity.Funcionario;
import com.sprint03.model.mapper.request.FuncionarioRequest;
import com.sprint03.model.mapper.response.FuncionarioResponse;
import com.sprint03.model.mapper.response.FuncionarioResponseMapper;
import com.sprint03.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sprint03.model.mapper.request.FuncionarioRequestMapper.paraEntidadeFuncionaio;
import static com.sprint03.model.mapper.response.FuncionarioResponseMapper.paraFuncionarioResponse;

@AllArgsConstructor
@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioResponse criaFuncionario(FuncionarioRequest funcionarioRequest){
            return paraFuncionarioResponse(repository.save(paraEntidadeFuncionaio(funcionarioRequest)));
    }

    public FuncionarioResponse buscaFuncionarioId(String id) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não encontrou ID " + id));
        return paraFuncionarioResponse(funcionario);
    }

    public void deletarFuncionarioPeloId(String id){
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não encontrou ID " + id));
        repository.deleteById(id);
    }

    public void deletarFuncionariosPelosIds(List<String> ids){
        repository.deleteAllById(ids);
    }

    public List<FuncionarioResponse> listarFuncionarios(){
        return repository.findAll().stream()
                .map(FuncionarioResponseMapper::paraFuncionarioResponse)
                .toList();
    }

    public FuncionarioResponse alterarFuncionarioId(FuncionarioRequest funcionarioRequest, String id) {
        Funcionario buscado = repository.findById(id).orElseThrow(() -> new NotFoundException("ID não encontrado."));
        buscado.setNome(funcionarioRequest.getNome());
        buscado.setDataNascimento(funcionarioRequest.getDataNascimento());
        Funcionario salvado = repository.save(buscado);
        return paraFuncionarioResponse(salvado);
    }

}
