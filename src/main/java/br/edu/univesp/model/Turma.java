package br.edu.univesp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Turma implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTurma;

	@NotBlank
	@NotNull
	@Column(length = 20, nullable = true)
	private String nome;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Curso curso;

	@NotBlank
	@NotNull
	@Column(length = 20, nullable = false)
	private String periodo;

	@NotNull
	@Column(nullable = false)
	private int serie;

	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "turma")
	private List<Aluno> alunos;

	public void adicionarAluno(Aluno novoAluno) {
		// Verificar se o aluno já existe na lista
		boolean alunoExistente = false;
		for (Aluno aluno : alunos) {
			if (aluno.getUsuario().getNome().equals(novoAluno.getUsuario().getNome())) {
				alunoExistente = true;
				break;
			}
		}

		// Se o aluno já existe, exibir uma mensagem ou lançar uma exceção
		if (alunoExistente) {
			System.out.println("O aluno já existe na turma.");
			// Ou, se preferir, lançar uma exceção
			// throw new IllegalArgumentException("O aluno já existe na turma.");
		} else {
			// Se o aluno não existe, adicionar à lista
			alunos.add(novoAluno);
			System.out.println("Aluno adicionada com sucesso.");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTurma);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		return Objects.equals(idTurma, other.idTurma);
	}

}
