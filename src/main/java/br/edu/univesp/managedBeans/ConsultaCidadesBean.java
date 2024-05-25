package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.repository.Cidades;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaCidadesBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private List<Cidade> cidades;

	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		Cidades cidades = new Cidades(manager);

		this.cidades = cidades.todas();

		manager.close();
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
