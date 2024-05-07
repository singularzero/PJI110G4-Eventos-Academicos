package br.edu.univesp.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Aluno;
import br.edu.univesp.model.Curso;
import br.edu.univesp.model.Turma;
import br.edu.univesp.model.Usuario;
import br.edu.univesp.util.JpaUtil;

public class CriaAluno {

	public static void main(String[] args) {
		// Cria o EntityManager
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Usuario usuario = manager.find(Usuario.class, 2L);

		if (usuario == null) {

			throw new IllegalArgumentException("Usuario não encontrado");

		}

		Curso curso = manager.find(Curso.class, 1L);

		if (curso == null) {

			throw new IllegalArgumentException("Curso não encontrado");

		}

		Turma turma = manager.find(Turma.class, 1L);

		if (turma == null) {

			throw new IllegalArgumentException("Turma não encontrado");

		}

		Aluno aluno = new Aluno();
		aluno.setAtivo(true);
		aluno.setUsuario(usuario);
		aluno.setCurso(curso);
		aluno.setTurma(turma);

		turma.adicionarAluno(aluno);

		manager.persist(aluno);
		trx.commit();
		manager.close();

	}

}
