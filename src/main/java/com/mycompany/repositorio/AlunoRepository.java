package com.mycompany.repositorio;

import com.mycompany.modelo.Aluno;
import org.springframework.data.repository.CrudRepository;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {
    public Long countById(Integer id);
}
