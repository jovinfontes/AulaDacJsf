package br.com.develop.view.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.develop.controller.services.EditoraService;
import br.com.develop.model.daos.EditoraDAO;
import br.com.develop.model.entities.Editora;
import br.com.develop.model.utils.JPAUtil;

@ManagedBean
@ViewScoped
public class CadastroEditoraBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Editora editora = new Editora();

	public void salvar() {
		EntityManager manager = JPAUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();

			EditoraService editoraService = new EditoraService(new EditoraDAO(manager));
			editoraService.salvar(editora);

			this.editora = new Editora();

			context.addMessage(null, new FacesMessage("Editora criada com sucesso!"));

			trx.commit();
		} catch (Exception e) {
			trx.rollback();

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		} finally {
			manager.close();
		}
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

}
