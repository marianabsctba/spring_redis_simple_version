package br.edu.infnet.tp3.controller;

import br.edu.infnet.tp3.model.Aluno;
import br.edu.infnet.tp3.model.Curso;
import br.edu.infnet.tp3.service.AlunoService;
import br.edu.infnet.tp3.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private CursoService cursoService;


    @GetMapping()
    public List<Aluno> listar() {
        return alunoService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Aluno> buscar(@PathVariable Integer id) {
        try {
            return alunoService.buscar(id);
        }
        catch (Exception e) {
            return Optional.empty();
        }

    }

    @PostMapping()
    public Aluno cadastrar(@RequestBody Aluno aluno) {
        Set<Curso> cursos = aluno.getCursos().stream()
                .map(curso -> cursoService.buscar(curso.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        aluno.setCursos(null);
        Aluno savedAluno = alunoService.cadastrar(aluno);
        savedAluno.setCursos(cursos);
        return alunoService.cadastrar(savedAluno);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Aluno aluno) {
        try {
            return ResponseEntity.ok(alunoService.atualizar(id, aluno));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        try {
            alunoService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
