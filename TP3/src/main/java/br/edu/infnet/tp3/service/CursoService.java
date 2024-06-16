package br.edu.infnet.tp3.service;

import br.edu.infnet.tp3.model.Curso;
import br.edu.infnet.tp3.model.Aluno;
import br.edu.infnet.tp3.repository.AlunoRepository;
import br.edu.infnet.tp3.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscar(Integer id) {
        return Optional.ofNullable(cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com o id: " + id)));
    }

    public Curso cadastrar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso atualizar(Integer id, Curso curso) {

        if (cursoRepository.existsById(id)) {
            curso.setId(id);
            return this.cadastrar(curso);
        }
        throw new RuntimeException("Curso não encontrado com o id: " + id);
        }

    public void excluir(Integer id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return;
        }

        throw new RuntimeException("Curso não encontrado com o id: " + id);
    }

    public Curso inserirCursoAluno(Integer id, Curso curso){
        return alunoRepository.findById(id)
                .map(aluno -> {
                    Set<Aluno> alunos = new HashSet<>();
                    curso.setAlunos(null);
                    Curso cursoSaved = cursoRepository.save(curso);
                    alunos.add(aluno);
                    cursoSaved.setAlunos(alunos);
                    return cursoRepository.save(cursoSaved);
                }).orElseThrow(() -> new RuntimeException("Aluno não encontrado com o id: "+id));
    }

}
