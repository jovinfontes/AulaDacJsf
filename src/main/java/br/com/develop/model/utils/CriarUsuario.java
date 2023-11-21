package br.com.develop.model.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.develop.model.entities.Usuario;

public class CriarUsuario {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("atividadePU");
        EntityManager manager = factory.createEntityManager();
        
        Usuario usuario = new Usuario();
        usuario.setUsername("fuleco");
        CriptografarSenha senhaNova = new CriptografarSenha();
		String pswd = senhaNova.encripta("fufu123");
		usuario.setSenha(pswd);
        
        manager.getTransaction().begin();
        manager.persist(usuario);
        manager.getTransaction().commit();
        manager.close();

	}

}
