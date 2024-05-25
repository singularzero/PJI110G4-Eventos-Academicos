package br.edu.univesp.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Aluno;
import br.edu.univesp.repository.Alunos;
import br.edu.univesp.service.CadastroAlunos;
import br.edu.univesp.service.NegocioException;
import br.edu.univesp.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaAlunosBean implements Serializable {

	/**
	 * Verderesi
	 */
	private static final long serialVersionUID = 1L;

	private List<Aluno> alunos;
	private Aluno alunoSelecionado;

	EntityManager manager = JpaUtil.getEntityManager();

	Alunos alunosRepository = new Alunos(manager);

	public void consultar() {

		Alunos alunos = new Alunos(manager);

		this.alunos = alunos.todos();

		manager.close();
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			CadastroAlunos cadastro = new CadastroAlunos(new Alunos(manager));
			cadastro.excluir(alunoSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage("Aluno excluído com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public Alunos getAlunosRepository() {
		return alunosRepository;
	}

	public void setAlunosRepository(Alunos alunosRepository) {
		this.alunosRepository = alunosRepository;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public List<Aluno> getalunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}