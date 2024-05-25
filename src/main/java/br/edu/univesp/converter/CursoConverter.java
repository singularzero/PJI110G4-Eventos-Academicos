package br.edu.univesp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Curso;
import br.edu.univesp.repository.Cursos;
import br.edu.univesp.util.JpaUtil;

@FacesConverter(forClass = Curso.class)
public class CursoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Curso retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				Cursos cursos = new Cursos(manager);
				retorno = cursos.porId(new Long(value));
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
		if (value instanceof Curso) {
			Curso curso = (Curso) value;
			Long idCurso = curso.getIdCurso();
			return (idCurso != null) ? idCurso.toString() : "";
		} else {
			throw new IllegalArgumentException("Objeto não é do tipo Curso");
		}
	}
}
