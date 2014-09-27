package br.curso.jsf2.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenericDAO<T> {
	private Class<T> classe;
	
	public GenericDAO(Class<T> classe) {
		super();
		this.classe = classe;
	}

	public void cadastrar(T t) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	public void excluir (T t) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}

	public void atualizar(T t) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<T> listarTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		
		List<T> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}

	public Long contarTodos() {
		EntityManager em = JPAUtil.getEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<T> alias = query.from(classe);
		query.select(builder.count(alias));
		
		Long result = em.createQuery(query).getSingleResult();
		em.close();
		return result;
	}
	
	public List<T> listarTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = JPAUtil.getEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(classe);
		query.select(query.from(classe));
		
		List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
		em.close();
		return lista;
	}
	
	public T buscarPorId(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		T instancia = em.find(classe, id);
		
		em.close();
		return instancia;
	}
}
