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
public class Estado implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idEstado;

	@Column(length = 5, nullable = false)
	private String sigla;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "estado")
	private List<Cidade> cidades;

	@Column(length = 50, nullable = false)
	private String nome;

	public void adicionarCidade(Cidade novaCidade) {
		// Verificar se a cidade já existe na lista
		boolean cidadeExistente = false;
		for (Cidade cidade : cidades) {
			if (cidade.getNome().equals(novaCidade.getNome())) {
				cidadeExistente = true;
				break;
			}
		}

		// Se a cidade já existe, exibir uma mensagem ou lançar uma exceção
		if (cidadeExistente) {
			System.out.println("A cidade já existe no estado.");
			// Ou, se preferir, lançar uma exceção
			// throw new IllegalArgumentException("A cidade já existe no estado.");
		} else {
			// Se a cidade não existe, adicionar à lista
			cidades.add(novaCidade);
			System.out.println("Cidade adicionada com sucesso.");
		}
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEstado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		return Objects.equals(idEstado, other.idEstado);
	}

}
