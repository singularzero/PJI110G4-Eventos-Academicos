package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Coordenador;
import br.edu.univesp.model.Usuario;
import br.edu.univesp.repository.Coordenadores;
import br.edu.univesp.repository.Usuarios;
import br.edu.univesp.service.CadastroCoordenadores;
import br.edu.univesp.service.NegocioException;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroCoordenadorBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Coordenador coordenador = new Coordenador();
	private List<Usuario> todosUsuarios;

	public void prepararCadastro() {
		EntityManager manager = JpaUtil.getEntityManager();

		try {
			Usuarios usuarios = new Usuarios(manager);
			this.todosUsuarios = usuarios.todos();
			if (this.coordenador == null) {
				this.coordenador = new Coordenador();
			}
		} finally {
			manager.close();
		}

	}

	public void salvar() {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			trx.begin();
			CadastroCoordenadores cadastro = new CadastroCoordenadores(new Coordenadores(manager));
			cadastro.salvar(this.coordenador);
			this.coordenador = new Coordenador();
			context.addMessage(null, new FacesMessage("Coordenador salvo com sucesso!"));

			trx.commit();
		} catch (NegocioException e) {
			trx.rollback();

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} finally {
			manager.close();
		}
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public List<Usuario> getTodosUsuarios() {
		return todosUsuarios;
	}

	public void setTodosUsuarios(List<Usuario> todosUsuarios) {
		this.todosUsuarios = todosUsuarios;
	}

}
