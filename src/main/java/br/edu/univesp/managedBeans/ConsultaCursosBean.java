package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Curso;
import br.edu.univesp.repository.Cursos;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaCursosBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private List<Curso> cursos;

	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		Cursos cursos = new Cursos(manager);

		this.cursos = cursos.todos();

		manager.close();
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}