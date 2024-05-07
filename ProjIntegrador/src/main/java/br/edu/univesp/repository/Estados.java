package br.edu.univesp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univesp.model.Estado;

public class Estados implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Estados(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public List<Estado> todos() {
		TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
		return query.getResultList();
	}

	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}

	public void adicionar(Estado estado) {
		this.manager.persist(estado);
	}

}
