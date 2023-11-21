package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.develop.controller.services.UsuarioService;
import br.com.develop.model.entities.Usuario;
import br.com.develop.model.utils.CriptografarSenha;

@Named
@SessionScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usuarioService;
	
	private Usuario usuario = new Usuario();

	private String nomeUsuario;
	private String senha;
	private Date dataLogin;
	

	public String login() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		CriptografarSenha senhaNova = new CriptografarSenha();
		String pswd = senhaNova.encripta(senha);
		this.usuario = usuarioService.loginUsuario(nomeUsuario, pswd);

		if (this.usuario.getUsername().equals(nomeUsuario) && this.usuario.getSenha().equals(pswd)) {
			this.setDataLogin(new Date());
			session.setAttribute("usuario", this.usuario);
			return "/listalivros-layout?faces-redirect=true";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		return null;

	}
	
	public String sair() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.usuario = new Usuario();
		return "/login?faces-redirect=true";
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}

}
