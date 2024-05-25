package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Aluno;
import br.edu.univesp.model.Curso;
import br.edu.univesp.model.Turma;
import br.edu.univesp.model.Usuario;
import br.edu.univesp.repository.Alunos;
import br.edu.univesp.repository.Cursos;
import br.edu.univesp.repository.Turmas;
import br.edu.univesp.repository.Usuarios;
import br.edu.univesp.service.CadastroAlunos;
import br.edu.univesp.service.NegocioException;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroAlunoBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Aluno aluno = new Aluno();
	private List<Usuario> todosUsuarios;
	private List<Curso> todosCursos;
	private List<Turma> todasTurmas;

	public void prepararCadastro() {
		EntityManager manager = JpaUtil.getEntityManager();

		try {
			Usuarios usuarios = new Usuarios(manager);
			this.todosUsuarios = usuarios.todos();

			Cursos cursos = new Cursos(manager);
			this.todosCursos = cursos.todos();

			Turmas turmas = new Turmas(manager);
			this.todasTurmas = turmas.todos();

			if (this.aluno == null) {
				this.aluno = new Aluno();
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
			CadastroAlunos cadastro = new CadastroAlunos(new Alunos(manager));
			cadastro.salvar(this.aluno);
			this.aluno = new Aluno();
			context.addMessage(null, new FacesMessage("Aluno salvo com sucesso!"));

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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Usuario> getTodosUsuarios() {
		return todosUsuarios;
	}

	public void setTodosUsuarios(List<Usuario> todosUsuarios) {
		this.todosUsuarios = todosUsuarios;
	}

	public List<Curso> getTodosCursos() {
		return todosCursos;
	}

	public void setTodosCursos(List<Curso> todosCursos) {
		this.todosCursos = todosCursos;
	}

	public List<Turma> getTodasTurmas() {
		return todasTurmas;
	}

	public void setTodasTurmas(List<Turma> todasTurmas) {
		this.todasTurmas = todasTurmas;
	}

}
