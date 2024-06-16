package br.edu.infnet.tp3.controller;


import br.edu.infnet.tp3.model.MaterialDidatico;
import br.edu.infnet.tp3.service.MaterialDidaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/material-didatico")
public class MaterialDidaticoController {
    @Autowired
    private MaterialDidaticoService materialDidaticoService;

    @GetMapping
    public List<MaterialDidatico> listarTodos(){
        return materialDidaticoService.listarTodos();
    }

    @PostMapping
    public MaterialDidatico cadastrar(@RequestBody MaterialDidatico material){
        return materialDidaticoService.cadastrar(material);
    }

    @GetMapping("/{id}")
    public Optional<?> buscar(@PathVariable String id){
        return materialDidaticoService.buscar(id);
    }

    @PutMapping("/{id}")
    public MaterialDidatico atualizar(@PathVariable String id, @RequestBody MaterialDidatico material){
        return materialDidaticoService.atualizar(id, material);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir (@PathVariable String id){
        materialDidaticoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
