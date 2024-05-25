package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.model.Usuario;
import br.edu.univesp.repository.Cidades;
import br.edu.univesp.repository.Usuarios;
import br.edu.univesp.service.CadastroUsuarios;
import br.edu.univesp.service.NegocioException;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private List<Cidade> todasCidades;

	public void prepararCadastro() {
		EntityManager manager = JpaUtil.getEntityManager();

		try {
			Cidades cidades = new Cidades(manager);
			this.todasCidades = cidades.todas();
			if (this.usuario == null) {
				this.usuario = new Usuario();
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
			CadastroUsuarios cadastro = new CadastroUsuarios(new Usuarios(manager));
			cadastro.salvar(this.usuario);
			this.usuario = new Usuario();
			context.addMessage(null, new FacesMessage("Usuario salvo com sucesso!"));

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Cidade> getTodasCidades() {
		return todasCidades;
	}

	public void setTodasCidades(List<Cidade> todasCidades) {
		this.todasCidades = todasCidades;
	}

}
