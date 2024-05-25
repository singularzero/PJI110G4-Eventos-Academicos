package br.edu.univesp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Cidade;
import br.edu.univesp.repository.Cidades;
import br.edu.univesp.util.JpaUtil;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cidade retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				Cidades cidades = new Cidades(manager);
				retorno = cidades.porId(new Long(value));
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
		if (value instanceof Cidade) {
			Cidade cidade = (Cidade) value;
			Long idCidade = cidade.getIdCidade();
			return (idCidade != null) ? idCidade.toString() : "";
		} else {
			throw new IllegalArgumentException("Objeto não é do tipo Cidade");
		}
	}
}
