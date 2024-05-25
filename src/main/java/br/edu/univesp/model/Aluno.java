package br.edu.univesp.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Aluno implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAluno;

	
	@Column(nullable = false)
	private boolean ativo;

	@NotNull
	@OneToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Curso curso;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Turma turma;

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAluno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(idAluno, other.idAluno);
	}

}
