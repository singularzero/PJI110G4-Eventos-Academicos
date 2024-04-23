package br.edu.univesp.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.model.Estado;

public class CriaCidade {

	public static void main(String[] args) {

		// Cria o EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoIntegradorPU");
		EntityManager em = emf.createEntityManager();

		EntityTransaction trx = em.getTransaction();
		trx.begin();
		// Cria um novo estado
		Estado estado = new Estado();
		estado.setNome("São Paulo");
		estado.setSigla("SP");

		// Cria uma nova cidade
		Cidade cidade1 = new Cidade();
		cidade1.setEstado(estado);
		cidade1.setNome("Assis");

		estado.adicionarCidade(cidade1);

	}

}
