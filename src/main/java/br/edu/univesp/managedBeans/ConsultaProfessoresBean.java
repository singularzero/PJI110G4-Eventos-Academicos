package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Professor;
import br.edu.univesp.repository.Professores;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaProfessoresBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private List<Professor> professores;

	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		Professores professores = new Professores(manager);

		this.professores = professores.todos();

		manager.close();
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

}