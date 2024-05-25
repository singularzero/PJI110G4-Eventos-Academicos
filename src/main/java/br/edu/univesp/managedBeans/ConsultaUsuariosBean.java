package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Usuario;
import br.edu.univesp.repository.Usuarios;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaUsuariosBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios;

	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		Usuarios usuarios = new Usuarios(manager);

		this.usuarios = usuarios.todos();

		manager.close();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}