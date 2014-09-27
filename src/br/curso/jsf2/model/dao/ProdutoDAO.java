package br.curso.jsf2.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class ProdutoDAO {
	private final EntityManager em;

	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}

	public List<String> buscarProdutos(String nome) {
		return em.
				createQuery(
						"SELECT p.nome from Produto p "
						+ "WHERE LOWER(p.nome) LIKE :pNome ",
						String.class
				
				).setParameter("pNome", "%" + nome.toLowerCase() + "%")
				.getResultList();
	}

}
