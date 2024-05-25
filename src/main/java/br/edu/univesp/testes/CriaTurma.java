package br.edu.univesp.testes;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Curso;
import br.edu.univesp.model.Turma;
import br.edu.univesp.util.JpaUtil;

public class CriaTurma {

	public static void main(String[] args) {
		// Cria o EntityManager
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		// Supondo que você tenha recebido o nome d0 curso, turma e serie como argumento
		String nomeCurso = "Direito";
		String nome = "Turma1";
		String periodo = "noturno";
		int serie = 2;

		// Verifica se a turma já existe no curso
		Curso curso = manager.createQuery("SELECT e FROM Curso e WHERE e.nome = :nome", Curso.class)
				.setParameter("nome", nomeCurso).getSingleResult();

		boolean turmaExistente = curso.getTurmas().stream().anyMatch(turma -> turma.getNome().equalsIgnoreCase(nome));

		if (turmaExistente) {
			System.out.println("Turma já existe no curso.");
		} else {
			// Se a turma não existe, cria uma nova turma e a associa ao curso
			Turma novaTurma = new Turma();
			novaTurma.setNome(nome);
			novaTurma.setPeriodo(periodo);
			novaTurma.setSerie(serie);
			novaTurma.setDataInicio(new Date());
			novaTurma.setCurso(curso);

			manager.persist(novaTurma);
			System.out.println("Turma criada com sucesso no curso " + curso.getNome());
		}

		trx.commit();
		manager.close();
	}

}
