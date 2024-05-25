package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Coordenador;
import br.edu.univesp.repository.Coordenadores;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaCoordenadoresBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private List<Coordenador> coordenadores;

	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		Coordenadores coordenadores = new Coordenadores(manager);

		this.coordenadores = coordenadores.todos();

		manager.close();
	}

	public List<Coordenador> getCoordenadors() {
		return coordenadores;
	}

	public void setCoordenadores(List<Coordenador> coordenadores) {
		this.coordenadores = coordenadores;
	}

	public List<Coordenador> getCoordenadores() {
		return coordenadores;
	}

}