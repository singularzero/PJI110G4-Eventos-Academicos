package br.edu.univesp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Turma;
import br.edu.univesp.repository.Turmas;
import br.edu.univesp.util.JpaUtil;

@FacesConverter(forClass = Turma.class)
public class TurmaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Turma retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				Turmas turmas = new Turmas(manager);
				retorno = turmas.porId(new Long(value));
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
		if (value instanceof Turma) {
			Turma turma = (Turma) value;
			Long idTurma = turma.getIdTurma();
			return (idTurma != null) ? idTurma.toString() : "";
		} else {
			throw new IllegalArgumentException("Objeto não é do tipo Turma");
		}
	}
}
