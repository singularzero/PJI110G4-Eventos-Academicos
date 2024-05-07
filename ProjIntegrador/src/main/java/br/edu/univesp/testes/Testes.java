package br.edu.univesp.testes;

import javax.persistence.EntityManager;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.util.JpaUtil;

public class Testes {

	public static void main(String[] args) {

		// Cria o EntityManager
		EntityManager manager = JpaUtil.getEntityManager();

		Cidade cidade = manager.find(Cidade.class, 1L);

		if (cidade == null) {

			throw new IllegalArgumentException("Estado não encontrado");

		}

		cidade.listarUsuarios();
	}
}
