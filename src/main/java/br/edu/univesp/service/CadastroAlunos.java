package br.edu.univesp.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.edu.univesp.model.Aluno;
import br.edu.univesp.repository.Alunos;

@ManagedBean
@ViewScoped
public class CadastroAlunos implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Alunos alunos;	

	public CadastroAlunos(Alunos alunos) {
		this.alunos = alunos;
	}
	
	

	public void salvar(Aluno aluno) throws NegocioException {
        if (aluno.getUsuario() == null) {
            throw new NegocioException("O usuário não pode ser nulo");
        } else if (aluno.getCurso() == null) {
            throw new NegocioException("O curso não pode ser nulo");
        } else if (aluno.getTurma() == null) {
            throw new NegocioException("A turma não pode ser nula");
        }      

		this.alunos.guardar(aluno);
	}
	

	//@Transactional
	public void excluir(Aluno aluno) throws NegocioException {
		aluno = this.alunos.porId(aluno.getIdAluno());
		
        if (aluno == null) {
            throw new NegocioException("Aluno não encontrado");
        }


        if (aluno.getUsuario() == null) {
            throw new NegocioException("Não é possível excluir um aluno com usuário null");
        }

		this.alunos.remover(aluno);
	}
}
