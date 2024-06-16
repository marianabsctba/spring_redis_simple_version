package br.edu.infnet.tp3.controller;

import br.edu.infnet.tp3.model.Curso;
import br.edu.infnet.tp3.service.CursoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Curso> buscar(@PathVariable Integer id) {
        try {
            return cursoService.buscar(id);
        }
        catch (Exception e) {
            return Optional.empty();
        }

    }

    @PostMapping()
    public Curso cadastrar(@RequestBody Curso curso) {
        return cursoService.cadastrar(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Curso curso) {
        try {
            return ResponseEntity.ok(cursoService.atualizar(id, curso));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        try {
            cursoService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/aluno/{id}")
    public Curso inserirCursoAluno(@PathVariable Integer id, @RequestBody Curso curso){
        try {
            return cursoService.inserirCursoAluno(id, curso);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
