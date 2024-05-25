package br.edu.univesp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.univesp.model.Usuario;

public class Usuarios implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public Usuarios(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public List<Usuario> todos() {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario", Usuario.class);
		return query.getResultList();
	}

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

	public void adicionar(Usuario usuario) {
		this.manager.persist(usuario);
	}

	public Usuario guardar(Usuario usuario) {
		return this.manager.merge(usuario);
	}
	
	public void remover(Usuario usuario) {
		this.manager.remove(usuario);
	}

}
