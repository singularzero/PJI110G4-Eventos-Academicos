package br.edu.univesp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Coordenador;
import br.edu.univesp.repository.Coordenadores;
import br.edu.univesp.util.JpaUtil;

@FacesConverter(forClass = Coordenador.class)
public class CoordenadorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Coordenador retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				Coordenadores coordenadores = new Coordenadores(manager);
				retorno = coordenadores.porId(new Long(value));
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
		if (value instanceof Coordenador) {
			Coordenador coordenador = (Coordenador) value;
			Long idCoordenador = coordenador.getIdCoordenador();
			return (idCoordenador != null) ? idCoordenador.toString() : "";
		} else {
			throw new IllegalArgumentException("Objeto não é do tipo Coordenador");
		}
	}
}
