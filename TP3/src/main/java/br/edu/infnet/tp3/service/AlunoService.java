package br.edu.infnet.tp3.service;

import br.edu.infnet.tp3.model.Aluno;
import br.edu.infnet.tp3.model.Curso;
import br.edu.infnet.tp3.repository.AlunoRepository;
import br.edu.infnet.tp3.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CursoRepository cursosRepository;

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscar(Integer id) {
        return Optional.ofNullable(alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o id: " + id)));
    }

    public Aluno cadastrar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Integer id, Aluno aluno) {
        if (alunoRepository.existsById(id)) {
            aluno.setId(id);
            return this.cadastrar(aluno);
        }
        throw new RuntimeException("Aluno não encontrado com o id: " + id);
    }

    public void excluir(Integer id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return;
        }
        throw new RuntimeException("Aluno não encontrado com o id: " + id);
    }
}
