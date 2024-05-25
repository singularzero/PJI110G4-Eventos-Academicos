package br.edu.univesp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Professor;
import br.edu.univesp.repository.Professores;
import br.edu.univesp.util.JpaUtil;

@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Professor retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				Professores professores = new Professores(manager);
				retorno = professores.porId(new Long(value));
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
		if (value instanceof Professor) {
			Professor professor = (Professor) value;
			Long idProfessor = professor.getIdProfessor();
			return (idProfessor != null) ? idProfessor.toString() : "";
		} else {
			throw new IllegalArgumentException("Objeto não é do tipo Professor");
		}
	}
}
