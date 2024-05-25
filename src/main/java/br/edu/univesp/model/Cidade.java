package br.edu.univesp.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cidade implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCidade;

	@NotBlank
	@NotNull
	@Column(length = 50, nullable = false)
	private String nome;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cidade")
	private List<Usuario> usuarios;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Estado estado;

	public void adicionarUsuario(Usuario usuario) {
		if (this.usuarios == null) {
			this.usuarios = new ArrayList<>();
		}
		this.usuarios.add(usuario);
		usuario.setCidade(this);// Define a cidade deste usuario como a cidade atual
	}

	public void listarUsuarios() {
		if (usuarios != null) {
			for (Usuario usuario : usuarios) {
				System.out.println("Nome: " + usuario.getNome() + ", Email: " + usuario.getEmail() + " CPF: "
						+ usuario.getCpf() + ", RG: " + usuario.getRg());
				// Adicione outros atributos que deseja listar aqui
			}
		} else {
			System.out.println("A lista de usuários está vazia.");
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		return Objects.equals(idCidade, other.idCidade);
	}

	@Override
	public String toString() {
		return "Cidade [idCidade=" + idCidade + ", nome=" + nome + ", usuarios=" + usuarios + ", estado=" + estado
				+ ", getUsuarios()=" + getUsuarios() + ", getIdCidade()=" + getIdCidade() + ", getNome()=" + getNome()
				+ ", getEstado()=" + getEstado() + ", hashCode()=" + hashCode() + "]";
	}

}
