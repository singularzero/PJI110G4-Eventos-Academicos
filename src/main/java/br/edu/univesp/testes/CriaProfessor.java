package br.edu.univesp.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Professor;
import br.edu.univesp.model.Usuario;
import br.edu.univesp.util.JpaUtil;

public class CriaProfessor {

	public static void main(String[] args) {
		// Cria o EntityManager
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Usuario usuario = manager.find(Usuario.class, 2L);

		if (usuario == null) {

			throw new IllegalArgumentException("Usuario não encontrado");

		}

		Professor professor = new Professor();
		professor.setUsuario(usuario);

		manager.persist(professor);
		trx.commit();
		manager.close();

	}

}
