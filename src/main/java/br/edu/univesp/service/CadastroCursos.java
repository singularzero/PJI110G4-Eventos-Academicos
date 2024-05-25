package br.edu.univesp.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;

import br.edu.univesp.model.Curso;
import br.edu.univesp.repository.Cursos;

@ManagedBean
@ViewScoped
public class CadastroCursos implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Cursos cursos;

	public CadastroCursos(Cursos cursos) {
		this.cursos = cursos;
	}

	public void salvar(Curso curso) throws NegocioException {
		if (curso.getNome() == null) {
			throw new NegocioException("O nome do curso não pode ser nula");
		}

		this.cursos.guardar(curso);
	}

	@Transactional
	public void excluir(Curso curso) throws NegocioException {
		curso = this.cursos.porId(curso.getIdCurso());

		if (curso.getTurmas() == null) {
			throw new NegocioException("Não é possível excluir um curso com turma null");
		}
		this.cursos.remover(curso);
	}

}
