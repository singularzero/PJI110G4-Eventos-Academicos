//pacote managedBeans - (controller)
package br.edu.univesp.managedBeans;

import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//Exemplo de um managedBean
@ManagedBean(name = "ola")
@ViewScoped
public class OlaBean {

	private String nome;
	private String sobrenome;
	private String nomeCompleto;

	public void dizerOla() {
		this.nomeCompleto = this.nome.toUpperCase() + " " + this.sobrenome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, nomeCompleto, sobrenome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OlaBean other = (OlaBean) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(nomeCompleto, other.nomeCompleto)
				&& Objects.equals(sobrenome, other.sobrenome);
	}

}
