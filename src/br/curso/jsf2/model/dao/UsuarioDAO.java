package br.curso.jsf2.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.curso.jsf2.model.bean.Usuario;

public class UsuarioDAO {
//	private final EntityManager em;
//	
//	public UsuarioDAO(EntityManager em) {
//		this.em = em;
//	}
	
	public boolean existe(Usuario usuario) {
		boolean resultado = false;
		EntityManager em = JPAUtil.getEntityManager();
//		em.getTransaction().begin();
		
		Query query = em.createQuery("FROM Usuario u "
								   + "WHERE u.login = :pLogin "
								   + "AND u.senha = :pSenha ");
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		
		resultado = !query.getResultList().isEmpty();
		
//		em.getTransaction().commit();
		em.close();
		
		return resultado;
	}
	
}
