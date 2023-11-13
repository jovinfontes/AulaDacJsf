package br.com.develop.view.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.services.EditoraService;
import br.com.develop.model.entities.Editora;

@Named
@ViewScoped
public class CadastroEditoraBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EditoraService editoraService;
	
	@Inject
	private Editora editora = new Editora();

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			editoraService.salvar(editora);
			this.editora = new Editora();
			context.addMessage(null, new FacesMessage("Editora criada com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} 
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

}
