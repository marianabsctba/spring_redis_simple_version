package br.edu.infnet.tp3.service;

import br.edu.infnet.tp3.model.Curso;
import br.edu.infnet.tp3.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoCacheService {

    @Autowired
    private CursoRepository cursoRepository;

    @Cacheable(value = "curso")
    public List<Curso> buscarCusrsos(){
        return cursoRepository.findAll();
    }

    @Cacheable(value = "curso", key = "#id")
    public Optional<Curso> buscarCursoPorId(Integer id){
        return cursoRepository.findById(id);
    }

    @CacheEvict(value = "curso",  key = "#id")
    public void excluirCurso(Integer id){
        cursoRepository.deleteById(id);
    }

    @CacheEvict(value = "curso",  key = "#id")
    public Curso atualizarCurso(Integer id, Curso updateCurso){
        return cursoRepository.findById(id)
                .map(curso -> {
                    curso.setNome(updateCurso.getNome());
                    curso.setDescricao(updateCurso.getDescricao());
                    curso.setAlunos(updateCurso.getAlunos());
                    return cursoRepository.save(curso);
                }).orElseGet(() -> {
                   updateCurso.setId(id);
                   return cursoRepository.save(updateCurso);
                });

    }
}