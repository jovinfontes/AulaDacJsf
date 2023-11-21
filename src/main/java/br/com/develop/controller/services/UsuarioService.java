package br.com.develop.controller.services;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.develop.model.daos.UsuarioDAO;
import br.com.develop.model.entities.Usuario;
import br.com.develop.model.utils.Transactional;

public class UsuarioService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Transactional
    public Usuario loginUsuario(String username, String password){
        return this.usuarioDAO.identificarUsuario(username, password);
    }
    
}
