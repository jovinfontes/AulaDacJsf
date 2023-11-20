package br.com.develop.view.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.develop.controller.exceptions.BusinessException;
import br.com.develop.controller.services.LivroService;
import br.com.develop.model.daos.EditoraDAO;
import br.com.develop.model.daos.LivroDAO;
import br.com.develop.model.entities.Editora;
import br.com.develop.model.entities.Livro;

@Named
@ViewScoped
public class ConsultaLivrosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LivroService livroService;

	@Inject
	private LivroDAO livroDAO;
	
	@Inject
	private EditoraDAO editoraDAO;

	private List<Livro> livros = new ArrayList<>();
	private List<Editora> editoras = new ArrayList<>();;
	private Livro livroSelecionado;

//	public void consultar() {
//		this.livros = livroDAO.todos();
//	}
	
	@PostConstruct
	public void init() {
		this.editoras = editoraDAO.todas();
		this.livros = livroDAO.todos();
	}


	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			livroService.salvar(livroSelecionado);
			this.livroSelecionado = new Livro();
			context.addMessage(null, new FacesMessage("Livro salvo com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.livroService.excluir(this.livroSelecionado);
			//this.consultar();
			context.addMessage(null, new FacesMessage("Livro excluído com sucesso!"));
		} catch (BusinessException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

}
