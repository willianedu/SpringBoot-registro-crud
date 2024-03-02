package com.mycompany.servico;

import com.mycompany.Exceptions.AlunoNotFoundException;
import com.mycompany.Exceptions.MatriculaDuplicadoException;
import com.mycompany.modelo.Aluno;
import com.mycompany.repositorio.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired private AlunoRepository repo;

    public List<Aluno> listAll() {
        return (List<Aluno>) repo.findAll();
    }

    public void save(Aluno aluno) {
        try {
            repo.save(aluno);
        } catch(DataIntegrityViolationException e ) {
            throw new MatriculaDuplicadoException("Já existe um aluno com esse número de matricula.");
        }
        }

    public Aluno get(Integer id) throws AlunoNotFoundException {
        Optional<Aluno> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new AlunoNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id) throws AlunoNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new AlunoNotFoundException("Could not find any users with ID " + id);
        }
        repo.deleteById(id);
    }
}
