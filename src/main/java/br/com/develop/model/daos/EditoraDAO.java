package br.com.develop.model.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.develop.model.entities.Editora;

public class EditoraDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	public EditoraDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public void addEditora(Editora editora) {
		manager.persist(editora);
	}
	
	public Editora porId(Long id) {
		return manager.find(Editora.class, id);
	}
	
	public List<Editora> todas() {
		TypedQuery<Editora> query = manager.createQuery("FROM Editora", Editora.class);
		return query.getResultList();
	}
}
