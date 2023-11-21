package br.com.develop.model.daos;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.develop.model.entities.Usuario;

public class UsuarioDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public UsuarioDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void addUsuario(Usuario usuario) {
		this.manager.persist(usuario);
	}

	public Usuario identificarUsuario(String username, String password) {
		Query query = manager
				.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.senha = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		Usuario result = null;
		try {
			result = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			// no result found
		}
		return result;
	}

}
