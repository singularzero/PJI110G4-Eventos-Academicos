package br.edu.univesp.managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Curso;
import br.edu.univesp.repository.Cursos;
import br.edu.univesp.service.CadastroCursos;
import br.edu.univesp.service.NegocioException;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroCursoBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Curso curso = new Curso();

	public void prepararCadastro() {
		
		try {
			
			if (this.curso == null) {
				this.curso = new Curso();
			}
		} finally {
			
		}
	}

	public void salvar() {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			trx.begin();
			CadastroCursos cadastro = new CadastroCursos(new Cursos(manager));
			cadastro.salvar(this.curso);
			this.curso = new Curso();
			context.addMessage(null, new FacesMessage("Curso salvo com sucesso!"));

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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
