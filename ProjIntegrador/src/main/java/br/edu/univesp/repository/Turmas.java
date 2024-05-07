package br.edu.univesp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univesp.model.Turma;

public class Turmas implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Turmas(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public List<Turma> todos() {
		TypedQuery<Turma> query = manager.createQuery("from Turma", Turma.class);
		return query.getResultList();
	}

	public Turma porId(Long id) {
		return manager.find(Turma.class, id);
	}

	public void adicionar(Turma turma) {
		this.manager.persist(turma);
	}

}
