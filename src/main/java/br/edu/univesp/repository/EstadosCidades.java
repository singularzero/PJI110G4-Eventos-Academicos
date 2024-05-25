package br.edu.univesp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univesp.model.Estado;

public class EstadosCidades implements Serializable{

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	public EstadosCidades(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public List<Estado> todos() {
		String jpql = "SELECT DISTINCT e FROM Estado e JOIN FETCH e.cidades";		
		TypedQuery<Estado> query = manager.createQuery(jpql, Estado.class);
		return query.getResultList();
	}

}
