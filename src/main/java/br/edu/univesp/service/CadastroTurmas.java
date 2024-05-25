package br.edu.univesp.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;

import br.edu.univesp.model.Turma;
import br.edu.univesp.repository.Turmas;

@ManagedBean
@ViewScoped
public class CadastroTurmas implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Turmas turmas;

	public CadastroTurmas(Turmas turmas) {
		this.turmas = turmas;
	}

	public void salvar(Turma turma) throws NegocioException {
		if (turma.getCurso() == null) {
			throw new NegocioException("O curso de uma turma não pode ser nula");
		}

		this.turmas.guardar(turma);
	}

	@Transactional
	public void excluir(Turma turma) throws NegocioException {
		turma = this.turmas.porId(turma.getIdTurma());

		if (turma.getCurso() == null) {
			throw new NegocioException("Não é possível excluir uma turma com curso null");
		}
		this.turmas.remover(turma);
	}

}
