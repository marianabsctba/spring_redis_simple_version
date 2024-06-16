package br.edu.infnet.tp3.service;

import br.edu.infnet.tp3.model.MaterialDidatico;
import br.edu.infnet.tp3.repository.MaterialDidaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDidaticoService {
    @Autowired
    private MaterialDidaticoRepository materialDidaticoRepository;

    public List<MaterialDidatico> listarTodos(){
        return materialDidaticoRepository.findAll();
    }

    public Optional<MaterialDidatico> buscar(String id){
        return materialDidaticoRepository.findById(id);
    }

    public MaterialDidatico cadastrar(MaterialDidatico material){
        return materialDidaticoRepository.save(material);
    }

    public MaterialDidatico atualizar(String id, MaterialDidatico materialUpdate){
        return materialDidaticoRepository.findById(id).map(material -> {
            material.setNome(materialUpdate.getNome());
            return materialDidaticoRepository.save(material);
        }).orElseGet(() -> {
            materialUpdate.setId(id);
            return materialDidaticoRepository.save(materialUpdate);
        });
    }

    public void excluir(String id){
        materialDidaticoRepository.deleteById(id);
    }

}
