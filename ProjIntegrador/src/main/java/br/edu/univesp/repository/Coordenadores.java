package br.edu.univesp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univesp.model.Coordenador;

public class Coordenadores implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Coordenadores(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public List<Coordenador> todos() {
		TypedQuery<Coordenador> query = manager.createQuery("from Coordenador", Coordenador.class);
		return query.getResultList();
	}

	public Coordenador porId(Long id) {
		return manager.find(Coordenador.class, id);
	}

	public void adicionar(Coordenador coordenador) {
		this.manager.persist(coordenador);
	}
}