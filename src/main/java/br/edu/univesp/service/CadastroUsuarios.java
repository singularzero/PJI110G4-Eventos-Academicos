package br.edu.univesp.service;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;

import br.edu.univesp.model.Usuario;
import br.edu.univesp.repository.Usuarios;

@ManagedBean
@ViewScoped
public class CadastroUsuarios implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Usuarios usuarios;

	public CadastroUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public void salvar(Usuario usuario) throws NegocioException {
		if (usuario.getCidade() == null) {
			throw new NegocioException("A cidade de um usuario não pode ser nula");
		}

		this.usuarios.guardar(usuario);
	}

	@Transactional
	public void excluir(Usuario usuario) throws NegocioException {
		usuario = this.usuarios.porId(usuario.getIdUsuario());

		if (usuario.getCpf() == null) {
			throw new NegocioException("Não é possível excluir um usuario com cpf null");
		}
		this.usuarios.remover(usuario);
	}

}
