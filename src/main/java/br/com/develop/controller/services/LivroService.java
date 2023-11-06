package br.com.develop.controller.services;

import java.io.Serializable;

import br.com.develop.model.daos.LivroDAO;
import br.com.develop.model.entities.Livro;

public class LivroService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LivroDAO livroDAO;

	public LivroService(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}
	
	public Livro search(Long id) {
		return this.livroDAO.buscarPorId(id);
	}
	
	public void salvar(Livro livro) {
		this.livroDAO.addLivro(livro);
	}

}
