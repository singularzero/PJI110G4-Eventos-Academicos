package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Curso;
import br.edu.univesp.model.Turma;
import br.edu.univesp.repository.Cursos;
import br.edu.univesp.repository.Turmas;
import br.edu.univesp.service.CadastroTurmas;
import br.edu.univesp.service.NegocioException;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroTurmaBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Turma turma = new Turma();
	private List<Curso> todosCursos;

	public void prepararCadastro() {
		EntityManager manager = JpaUtil.getEntityManager();

		try {
			Cursos cursos = new Cursos(manager);
			this.todosCursos = cursos.todos();
			if (this.turma == null) {
				this.turma = new Turma();
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
			CadastroTurmas cadastro = new CadastroTurmas(new Turmas(manager));
			cadastro.salvar(this.turma);
			this.turma = new Turma();
			context.addMessage(null, new FacesMessage("Turma salva com sucesso!"));

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

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Curso> getTodosCursos() {
		return todosCursos;
	}

	public void setTodosCursos(List<Curso> todosCursos) {
		this.todosCursos = todosCursos;
	}

}
