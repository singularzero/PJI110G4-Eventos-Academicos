package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Estado;
import br.edu.univesp.repository.EstadosCidades;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaEstadoCidadeBean implements Serializable {

	/**
	 * David
	 */
	private static final long serialVersionUID = 1L;

	private List<Estado> estados;

	public void consultar() {

		EntityManager manager = JpaUtil.getEntityManager();
		EstadosCidades estadosCidades = new EstadosCidades(manager);

		this.estados = estadosCidades.todos();
		manager.close();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
