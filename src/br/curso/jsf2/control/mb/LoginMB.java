package br.curso.jsf2.control.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.curso.jsf2.model.bean.Usuario;
import br.curso.jsf2.model.dao.UsuarioDAO;

@ManagedBean
@SessionScoped
public class LoginMB {
	private Usuario usuario = new Usuario();
	private boolean usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(boolean usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String efetuarLogin() {
		UsuarioDAO dao = new UsuarioDAO();
		boolean loginValido = dao.existe(usuario);
		
		if (loginValido) {
			usuarioLogado = true;
			return "mainpage?faces-redirect=true";
//			return "produto";
		}
		else {
			usuarioLogado = false;
			return "login";
//			return "login?faces-redirect=true";
		}
	}
	
	public String efetuarLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "login?faces-rdirect=true";
	}
}
