package br.edu.univesp.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.model.Usuario;
import br.edu.univesp.util.JpaUtil;

public class CriaUsuario {

	public static void main(String[] args) {
		// Cria o EntityManager
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		// Supondo que você tenha recebido o nome e o email do usuário como argumentos
		String nomeUsuario = "Felipe";
		String emailUsuario = "felipe@example.com";

		Cidade cidade = manager.find(Cidade.class, 1L);

		if (cidade == null) {

			throw new IllegalArgumentException("Cidade não encontrada");
		}

		// Verifica se o usuário já existe no banco de dados
		try {
			Usuario usuarioExistente = manager
					.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
					.setParameter("email", emailUsuario).getSingleResult();

			System.out.println(
					"Usuário já existe com o email: " + emailUsuario + "  " + "Nome: " + usuarioExistente.getNome());
		} catch (NoResultException e) {
			// Se o usuário não existe, cria um novo usuário
			Usuario novoUsuario = new Usuario();
			novoUsuario.setNome(nomeUsuario);
			novoUsuario.setEmail(emailUsuario);
			novoUsuario.setCpf("123");
			novoUsuario.setRg("333");
			novoUsuario.setCidade(cidade);
			cidade.adicionarUsuario(novoUsuario);
			manager.persist(novoUsuario);
			manager.persist(cidade);
			System.out.println("Usuário criado com sucesso.");
		}

		trx.commit();
		manager.close();
	}
}
