package br.edu.infnet.tp3.controller;

import br.edu.infnet.tp3.model.CacheCurso;
import br.edu.infnet.tp3.model.Curso;
import br.edu.infnet.tp3.service.CursoCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cache/cursos")
public class CursoCacheController {
    @Autowired
    private CursoCacheService cursoCacheService;

    @GetMapping
    public List<Curso> buscarCursos(){
        return cursoCacheService.buscarCusrsos();
    }
    @GetMapping("/{id}")
    public Optional<Curso> buscarCursoPorId(@PathVariable Integer id){
        return cursoCacheService.buscarCursoPorId(id);
    }

}
