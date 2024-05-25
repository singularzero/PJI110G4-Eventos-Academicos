package br.edu.univesp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univesp.model.Professor;

public class Professores implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Professores(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public List<Professor> todos() {
		TypedQuery<Professor> query = manager.createQuery("from Professor", Professor.class);
		return query.getResultList();
	}

	public Professor porId(Long id) {
		return manager.find(Professor.class, id);
	}

	public void adicionar(Professor professor) {
		this.manager.persist(professor);
	}
	
	public Professor guardar(Professor professor) {
		return this.manager.merge(professor);
	}
	
	public void remover(Professor professor) {
		this.manager.remove(professor);
	}

}
