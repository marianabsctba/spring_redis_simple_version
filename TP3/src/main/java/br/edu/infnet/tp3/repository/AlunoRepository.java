package br.edu.infnet.tp3.repository;

import br.edu.infnet.tp3.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
