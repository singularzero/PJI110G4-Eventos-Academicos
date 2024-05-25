package br.edu.univesp.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.model.Estado;

public class AdicionaCidadeEstado {

	public static void main(String[] args) {
		// Cria o EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoIntegradorPU");
		EntityManager em = emf.createEntityManager();

		// Nova Cidade
		Cidade cidade = new Cidade();
		cidade.setNome("Assis");

		// Novo Estado
		Estado estado = new Estado();
		estado.setNome("São Paulo");
		estado.setSigla("SP");
		estado.adicionarCidade(cidade);

		// Persistindo o objeto
		em.getTransaction().begin();
		em.persist(estado);
		em.getTransaction().commit();

	}

}
