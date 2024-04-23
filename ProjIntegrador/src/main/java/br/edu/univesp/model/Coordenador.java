package br.edu.univesp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Coordenador implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCoordenador;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@OneToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column(nullable = false)
	private boolean liberado;

	public Long getIdCoordenador() {
		return idCoordenador;
	}

	public void setIdCoordenador(Long idCoordenador) {
		this.idCoordenador = idCoordenador;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLiberado() {
		return liberado;
	}

	public void setLiberado(boolean liberado) {
		this.liberado = liberado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCoordenador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenador other = (Coordenador) obj;
		return Objects.equals(idCoordenador, other.idCoordenador);
	}

}
