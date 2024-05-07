package br.edu.univesp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univesp.model.Aluno;

public class Alunos implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Alunos(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public List<Aluno> todos() {
		TypedQuery<Aluno> query = manager.createQuery("from Aluno", Aluno.class);
		return query.getResultList();
	}

	public Aluno porId(Long id) {
		return manager.find(Aluno.class, id);
	}

	public void adicionar(Aluno aluno) {
		this.manager.persist(aluno);
	}

}
