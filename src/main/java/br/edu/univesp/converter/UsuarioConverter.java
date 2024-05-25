package br.edu.univesp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.edu.univesp.model.Usuario;
import br.edu.univesp.repository.Usuarios;
import br.edu.univesp.util.JpaUtil;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				Usuarios usuarios = new Usuarios(manager);
				retorno = usuarios.porId(new Long(value));
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
		if (value instanceof Usuario) {
			Usuario usuario = (Usuario) value;
			Long idUsuario = usuario.getIdUsuario();
			return (idUsuario != null) ? idUsuario.toString() : "";
		} else {
			throw new IllegalArgumentException("Objeto não é do tipo Usuario");
		}
	}
}
