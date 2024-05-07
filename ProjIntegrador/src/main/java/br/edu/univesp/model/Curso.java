package br.edu.univesp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Curso implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCurso;

	@Column(length = 50, nullable = false)
	private String nome;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "curso")
	private List<Turma> turmas;

	public void adicionarTurma(Turma novaTurma) {
		// Verificar se a turma já existe na lista
		boolean turmaExistente = false;
		for (Turma turma : turmas) {
			if (turma.getNome().equals(novaTurma.getNome())) {
				turmaExistente = true;
				break;
			}
		}
		// Se a turma já existe, exibir uma mensagem ou lançar uma exceção
		if (turmaExistente) {
			System.out.println("A turma já existe no curso.");
			// Ou, se preferir, lançar uma exceção
			// throw new IllegalArgumentException("A cidade já existe no estado.");
		} else {
			// Se a cidade não existe, adicionar à lista
			turmas.add(novaTurma);
			System.out.println("Turma adicionada com sucesso.");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCurso);
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(idCurso, other.idCurso);
	}

}
