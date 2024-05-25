package br.edu.univesp.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;

import br.edu.univesp.model.Coordenador;
import br.edu.univesp.repository.Coordenadores;

@ManagedBean
@ViewScoped
public class CadastroCoordenadores implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Coordenadores coordenadores;

	public CadastroCoordenadores(Coordenadores coordenadores) {
		this.coordenadores = coordenadores;
	}

	public void salvar(Coordenador coordenador) throws NegocioException {
		if (coordenador.getUsuario() == null) {
			throw new NegocioException("O usuario não pode ser nula");
		}
		this.coordenadores.guardar(coordenador);
	}

	@Transactional
	public void excluir(Coordenador coordenador) throws NegocioException {
		coordenador = this.coordenadores.porId(coordenador.getIdCoordenador());

		if (coordenador.getUsuario() == null) {
			throw new NegocioException("Não é possível excluir um coordenador com usuario  null");
		}
		this.coordenadores.remover(coordenador);
	}
}
