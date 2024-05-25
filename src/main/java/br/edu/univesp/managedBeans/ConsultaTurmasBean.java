package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Turma;
import br.edu.univesp.repository.Turmas;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaTurmasBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private List<Turma> turmas;

	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		Turmas turmas = new Turmas(manager);

		this.turmas = turmas.todos();

		manager.close();
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

}