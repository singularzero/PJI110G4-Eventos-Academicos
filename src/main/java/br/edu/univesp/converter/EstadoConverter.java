package br.edu.univesp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Estado;
import br.edu.univesp.repository.Estados;
import br.edu.univesp.util.JpaUtil;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estado retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				Estados estados = new Estados(manager);
				retorno = estados.porId(new Long(value));
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
		if (value instanceof Estado) {
			Estado estado = (Estado) value;
			Long idEstado = estado.getIdEstado();
			return (idEstado != null) ? idEstado.toString() : "";
		} else {
			throw new IllegalArgumentException("Objeto não é do tipo Estado");
		}
	}
}
