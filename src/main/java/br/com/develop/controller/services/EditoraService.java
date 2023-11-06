package br.com.develop.controller.services;

import java.io.Serializable;

import br.com.develop.model.daos.EditoraDAO;
import br.com.develop.model.entities.Editora;

public class EditoraService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EditoraDAO editoraDAO;

	public EditoraService(EditoraDAO editoraDAO) {
		this.editoraDAO = editoraDAO;
	}
	
	public void salvar(Editora editora) {
		this.editoraDAO.addEditora(editora);
	}

}