package br.edu.univesp.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.univesp.model.Estado;

//Classe para testar a persistência de um estado
//Esta fora de padrão de projeto
public class CriaEstado {

	public static void main(String[] args) {

		// Cria o EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoIntegradorPU");
		EntityManager em = emf.createEntityManager();

		// Novo Estado
		Estado estado = new Estado();
		estado.setNome("São Paulo");
		estado.setSigla("SP");

		// Persistindo o objeto
		em.getTransaction().begin();
		em.persist(estado);
		em.getTransaction().commit();

	}

}
