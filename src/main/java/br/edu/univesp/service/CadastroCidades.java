package br.edu.univesp.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.repository.Cidades;

@ManagedBean
@ViewScoped
public class CadastroCidades implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Cidades cidades;

	public CadastroCidades(Cidades cidades) {
		this.cidades = cidades;
	}

	public void salvar(Cidade cidade) throws NegocioException {
		if (cidade.getEstado() == null) {
			throw new NegocioException("O estado de uma cidade não pode ser nula");
		}

		this.cidades.guardar(cidade);
	}

	@Transactional
	public void excluir(Cidade cidade) throws NegocioException {
		cidade = this.cidades.porId(cidade.getIdCidade());

		if (cidade.getEstado() == null) {
			throw new NegocioException("Não é possível excluir uma cidade com estado null");
		}
		this.cidades.remover(cidade);
	}

}
