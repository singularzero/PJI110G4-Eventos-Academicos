package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Professor;
import br.edu.univesp.model.Usuario;
import br.edu.univesp.repository.Professores;
import br.edu.univesp.repository.Usuarios;
import br.edu.univesp.service.CadastroProfessores;
import br.edu.univesp.service.NegocioException;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroProfessorBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Professor professor = new Professor();
	private List<Usuario> todosUsuarios;

	public void prepararCadastro() {
		EntityManager manager = JpaUtil.getEntityManager();

		try {
			Usuarios usuarios = new Usuarios(manager);
			this.todosUsuarios = usuarios.todos();
			if (this.professor == null) {
				this.professor = new Professor();
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
			CadastroProfessores cadastro = new CadastroProfessores(new Professores(manager));
			cadastro.salvar(this.professor);
			this.professor = new Professor();
			context.addMessage(null, new FacesMessage("Professor salvo com sucesso!"));

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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Usuario> getTodosUsuarios() {
		return todosUsuarios;
	}

	public void setTodosUsuarios(List<Usuario> todosUsuarios) {
		this.todosUsuarios = todosUsuarios;
	}

}
