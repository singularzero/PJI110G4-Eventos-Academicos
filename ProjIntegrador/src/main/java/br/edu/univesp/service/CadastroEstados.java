package br.edu.univesp.service;

import java.io.Serializable;

import br.edu.univesp.model.Estado;
import br.edu.univesp.repository.Estados;

public class CadastroEstados implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Estados estados;

	public CadastroEstados(Estados estados) {
		this.estados = estados;
	}

	public void salvar(Estado estado) throws NegocioException {
		if (estado.getSigla() == null) {
			throw new NegocioException("A sigla do estado não pode ser nula");
		}

		this.estados.adicionar(estado);
	}
}
