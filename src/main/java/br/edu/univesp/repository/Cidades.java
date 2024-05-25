package br.edu.univesp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univesp.model.Cidade;

public class Cidades implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Cidades(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public List<Cidade> todas() {
		TypedQuery<Cidade> query = manager.createQuery("from Cidade", Cidade.class);
		return query.getResultList();
	}

	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}

	public void adicionar(Cidade cidade) {
		this.manager.persist(cidade);
	}
	
	public Cidade guardar(Cidade cidade) {
		return this.manager.merge(cidade);
	}
	
	public void remover(Cidade cidade) {
		this.manager.remove(cidade);
	}

}
