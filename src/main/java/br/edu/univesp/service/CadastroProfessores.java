package br.edu.univesp.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;

import br.edu.univesp.model.Professor;
import br.edu.univesp.repository.Professores;

@ManagedBean
@ViewScoped
public class CadastroProfessores implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Professores professores;

	public CadastroProfessores(Professores professores) {
		this.professores = professores;
	}

	public void salvar(Professor professor) throws NegocioException {
		if (professor.getUsuario() == null) {
			throw new NegocioException("O usuario não pode ser nula");
		}

		this.professores.guardar(professor);
	}

	@Transactional
	public void excluir(Professor professor) throws NegocioException {
		professor = this.professores.porId(professor.getIdProfessor());

		if (professor.getUsuario() == null) {
			throw new NegocioException("Não é possível excluir um professor com ususrio null");
		}
		this.professores.remover(professor);
	}

}
