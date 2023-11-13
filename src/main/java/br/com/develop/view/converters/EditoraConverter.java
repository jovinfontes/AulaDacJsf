package br.com.develop.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.develop.model.daos.EditoraDAO;
import br.com.develop.model.entities.Editora;
import br.com.develop.model.utils.JPAUtil;

@FacesConverter(forClass = Editora.class, value="editoraConverter")
public class EditoraConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Editora retorno = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				EditoraDAO editora = new EditoraDAO(manager);
				retorno = editora.porId(new Long(value));
			}
			return retorno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Editora) value).getCodigo().toString();
		}
		return null;
	}
}
