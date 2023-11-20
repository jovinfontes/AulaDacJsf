package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.services.EditoraService;
import br.com.develop.model.daos.EditoraDAO;
import br.com.develop.model.daos.LivroDAO;
import br.com.develop.model.entities.Editora;
import br.com.develop.model.entities.Livro;

@Named
@ViewScoped
public class CadastroEditoraBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EditoraService editoraService;
	
	@Inject
	private EditoraDAO editoraDAO;
	
	@Inject
	private LivroDAO livroDAO;
	
	private Editora editora = new Editora();
	private List<Editora> editoras;
	private List<Livro> livros; 
	
	
	public void prepararCadastro() {
		this.livros = this.livroDAO.todos();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.editoraService.salvar(this.editora);
			this.editora = new Editora();
			context.addMessage(null, new FacesMessage("Editora criada com sucesso!"));
		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public void listarEditoras() {
		 this.editoras = editoraDAO.todas();
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
}
