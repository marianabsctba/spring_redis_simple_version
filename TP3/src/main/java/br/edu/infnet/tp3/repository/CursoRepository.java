package br.edu.infnet.tp3.repository;

import br.edu.infnet.tp3.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
