package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.model.daos.EditoraDAO;
import br.com.develop.model.entities.Editora;

@Named
@ViewScoped
public class ConsultaEditorasBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Editora> editoras;
	
	@Inject
	private EditoraDAO editoraDAO;

	@PostConstruct
	public void init() {
		this.editoras = editoraDAO.todas();
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

}
