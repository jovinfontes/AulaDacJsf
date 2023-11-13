package br.com.develop.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.develop.model.daos.LivroDAO;
import br.com.develop.model.entities.Livro;
import br.com.develop.model.utils.JPAUtil;

@FacesConverter(forClass = Livro.class, value="livroConverter")
public class LivroConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Livro retorno = null;
		EntityManager manager = JPAUtil.getEntityManager();
		try {
			if (value != null && !"".equals(value)) {
				LivroDAO livro = new LivroDAO(manager);
				retorno = livro.buscarPorId(new Long(value));
			}
			return retorno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Livro) value).getId().toString();
		}
		return null;
	}
}
