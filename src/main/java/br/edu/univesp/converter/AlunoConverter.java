package br.edu.univesp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Aluno;
import br.edu.univesp.repository.Alunos;
import br.edu.univesp.util.JpaUtil;

@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Aluno retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				Alunos alunos = new Alunos(manager);
				retorno = alunos.porId(new Long(value));
			}
			return retorno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	    if (value == null) {
	        return "";
	    }
	    if (value instanceof Aluno) {
			Aluno aluno = (Aluno) value;
	        Long idAluno = aluno.getIdAluno();
	        return (idAluno != null) ? idAluno.toString() : "";
	    } else {
	        throw new IllegalArgumentException("Objeto não é do tipo Aluno");
		}
	}
}
