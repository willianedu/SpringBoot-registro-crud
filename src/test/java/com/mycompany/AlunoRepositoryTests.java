package com.mycompany;

import com.mycompany.modelo.Aluno;
import com.mycompany.repositorio.AlunoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AlunoRepositoryTests {
    @Autowired private AlunoRepository repo;

    @Test
    public void testAddNew() {
        Aluno aluno = new Aluno();
        aluno.setCurso("Direito");
        aluno.setMatricula("AE1293812");
        aluno.setNome("Alex");
        aluno.setTurno("Matutino");

        Aluno savedAluno = repo.save(aluno);

        Assertions.assertThat(savedAluno).isNotNull();
        Assertions.assertThat(savedAluno.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Aluno> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (Aluno aluno : users) {
            System.out.println(aluno);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<Aluno> optionalUser = repo.findById(userId);
        Aluno aluno = optionalUser.get();
        aluno.setMatricula("hello2000");
        repo.save(aluno);

        Aluno updatedAluno = repo.findById(userId).get();
        Assertions.assertThat(updatedAluno.getMatricula()).isEqualTo("hello2000");
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<Aluno> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<Aluno> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
