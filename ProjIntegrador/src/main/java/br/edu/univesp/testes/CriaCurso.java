package br.edu.univesp.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Curso;
import br.edu.univesp.util.JpaUtil;

public class CriaCurso {

	public static void main(String[] args) {
		// Cria o EntityManager
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Curso curso = new Curso();
		curso.setNome("Direito");

		manager.persist(curso);
		trx.commit();
		manager.close();

	}

}
