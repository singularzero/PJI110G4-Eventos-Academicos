package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.model.Estado;
import br.edu.univesp.repository.Cidades;
import br.edu.univesp.repository.Estados;
import br.edu.univesp.service.CadastroCidades;
import br.edu.univesp.service.NegocioException;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class CadastroCidadeBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private Cidade cidade = new Cidade();
	private List<Estado> todosEstados;

	public void prepararCadastro() {
		EntityManager manager = JpaUtil.getEntityManager();

		try {
			Estados estados = new Estados(manager);
			this.todosEstados = estados.todos();
			if (this.cidade == null) {
				this.cidade = new Cidade();
			}	
		} finally {
			manager.close();
		}
	}

	public void salvar() {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			trx.begin();
			CadastroCidades cadastro = new CadastroCidades(new Cidades(manager));
			cadastro.salvar(this.cidade);
			this.cidade = new Cidade();
			context.addMessage(null, new FacesMessage("Cidade salva com sucesso!"));

			trx.commit();
		} catch (NegocioException e) {
			trx.rollback();

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} finally {
			manager.close();
		}
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Estado> getTodosEstados() {
		return todosEstados;
	}

	public void setTodosEstados(List<Estado> todosEstados) {
		this.todosEstados = todosEstados;
	}

}
