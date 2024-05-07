package br.edu.univesp.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.model.Estado;
import br.edu.univesp.util.JpaUtil;

public class CriaCidade {

	public static void main(String[] args) {

		// Cria o EntityManager
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		// Supondo que você tenha recebido o nome da cidade e a sigla do estado como
		// argumentos
		String nomeCidade = "Londrina";
		String siglaEstado = "PR";

		// Verifica se a cidade já existe no estado
		Estado estado = manager.createQuery("SELECT e FROM Estado e WHERE e.sigla = :sigla", Estado.class)
				.setParameter("sigla", siglaEstado).getSingleResult();

		boolean cidadeExistente = estado.getCidades().stream()
				.anyMatch(cidade -> cidade.getNome().equalsIgnoreCase(nomeCidade));

		if (cidadeExistente) {
			System.out.println("Cidade já existe no estado.");
		} else {
			// Se a cidade não existe, cria uma nova cidade e a associa ao estado
			Cidade novaCidade = new Cidade();
			novaCidade.setNome("Londrina");
			novaCidade.setEstado(estado);
			manager.persist(novaCidade);
			System.out.println("Cidade criada com sucesso no estado " + estado.getNome());
		}

		trx.commit();
		manager.close();
	}

}
