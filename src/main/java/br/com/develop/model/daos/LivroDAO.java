package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.Livro;

public class LivroDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public LivroDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public void addLivro(Livro livro) {
		//manager.persist(livro);
		this.guardar(livro);
	}
	
	public Livro guardar(Livro livro) {
		return manager.merge(livro);
	}
	
	public Livro buscarPorId(Long id) {
		return manager.find(Livro.class, id);
	}
	
	public void remover(Livro livro) {
		this.manager.remove(livro);
	}
	
	public List<Livro> todos() {
		TypedQuery<Livro> query = manager.createQuery("FROM Livro", Livro.class);
		return query.getResultList();
	}


}