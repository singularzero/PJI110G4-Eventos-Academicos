package br.edu.univesp.testes;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Coordenador;
import br.edu.univesp.model.Usuario;
import br.edu.univesp.util.JpaUtil;

public class CriaCoordenador {

	public static void main(String[] args) {
		// Cria o EntityManager
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Usuario usuario = manager.find(Usuario.class, 1L);

		if (usuario == null) {

			throw new IllegalArgumentException("Usuario não encontrado");

		}

		Coordenador coordenador = new Coordenador();
		coordenador.setUsuario(usuario);
		coordenador.setDataCadastro(new Date());
		coordenador.setLiberado(true);

		manager.persist(coordenador);
		trx.commit();
		manager.close();

	}

}
