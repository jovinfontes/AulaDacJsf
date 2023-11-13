package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.services.LivroService;
import br.com.develop.model.daos.EditoraDAO;
import br.com.develop.model.entities.Editora;
import br.com.develop.model.entities.Livro;

@Named
@ViewScoped
public class CadastroLivroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idLivro;
	private Livro livro = new Livro();
	private List<Editora> editoras;
	
	@Inject
	private LivroService livroService;

	@Inject
	private EditoraDAO editoraDAO;

	@PostConstruct
	public void init() {
		editoras = editoraDAO.todas();
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			livroService.salvar(livro);
			this.livro = new Livro();
			context.addMessage(null, new FacesMessage("Livro salvo com sucesso!"));

		} catch (Exception e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} 
	}

	public Long getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}

}
