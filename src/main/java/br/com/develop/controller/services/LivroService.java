package br.com.develop.controller.services;

import java.io.Serializable;

import javax.inject.Inject;


import br.com.develop.model.daos.LivroDAO;
import br.com.develop.model.entities.Livro;
import br.com.develop.model.utils.Transactional;

public class LivroService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDAO livroDAO;

	public LivroService(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}
	
	public Livro search(Long id) {
		return this.livroDAO.buscarPorId(id);
	}
	
	@Transactional
	public void salvar(Livro livro) throws BusinessException{
		if (livro == null) {
			throw new BusinessException("Não foi possível salvar o Livro.");
		}
		this.livroDAO.addLivro(livro);
	}
	
	@Transactional
	public void excluir(Livro livro) throws BusinessException {
		livro = this.livroDAO.buscarPorId(livro.getId());
		if (livro == null) {
			throw new BusinessException("Não é possível excluir o Livro!");
		}
		this.livroDAO.remover(livro);
	}

}
