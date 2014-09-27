package br.curso.jsf2.control.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.curso.jsf2.model.bean.Usuario;
import br.curso.jsf2.model.dao.UsuarioDAO;

@ManagedBean
@SessionScoped
public class LoginMB {
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String efetuarLogin() {
		UsuarioDAO dao = new UsuarioDAO();
		boolean loginValido = dao.existe(usuario);
		
		if (loginValido) {
			return "produto?faces-redirect=true";
//			return "produto";
		}
		else return "login?faces-redirect=true";
//			return "login";
		
	}
}
