package br.edu.univesp.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Estado;
import br.edu.univesp.util.JpaUtil;

//Classe para testar a persistência de um estado
//Esta fora de padrão de projeto
public class CriaEstado {

	public static void main(String[] args) {

		// Cria o EntityManager
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Estado estado1 = new Estado();
		estado1.setNome("São Paulo");
		estado1.setSigla("SP");

		Estado estado2 = new Estado();
		estado2.setNome("Minas Gerais");
		estado2.setSigla("MG");

		Estado estado3 = new Estado();
		estado3.setNome("Santa Catarina");
		estado3.setSigla("SC");

		Estado estado4 = new Estado();
		estado4.setNome("Parana");
		estado4.setSigla("PR");

		Estado estado5 = new Estado();
		estado5.setNome("Rio Grande do Sul");
		estado5.setSigla("RS");

		manager.persist(estado1);
		manager.persist(estado2);
		manager.persist(estado3);
		manager.persist(estado4);
		manager.persist(estado5);

		trx.commit();
		manager.close();

	}

}
