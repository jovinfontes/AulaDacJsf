package br.com.develop.controller.services;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.model.daos.EditoraDAO;
import br.com.develop.model.entities.Editora;
import br.com.develop.model.utils.Transactional;

public class EditoraService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EditoraDAO editoraDAO;

	@Transactional
	public void salvar(Editora editora) throws BusinessException {
		if (editora == null) {
			throw new BusinessException("Não foi possível salvar a Editora.");
		}
		this.editoraDAO.guardar(editora);
	}
	
	@Transactional
	public void excluir(Editora editora) throws BusinessException {
		editora = this.editoraDAO.porId(editora.getCodigo());
		if (editora == null) {
			throw new BusinessException("Não é possível excluir a Editora!");
		}
		this.editoraDAO.remover(editora);
	}

}