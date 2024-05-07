package br.edu.univesp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univesp.model.Curso;

public class Cursos implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Cursos(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public List<Curso> todos() {
		TypedQuery<Curso> query = manager.createQuery("from Curso", Curso.class);
		return query.getResultList();
	}

	public Curso porId(Long id) {
		return manager.find(Curso.class, id);
	}

	public void adicionar(Curso curso) {
		this.manager.persist(curso);
	}

}
