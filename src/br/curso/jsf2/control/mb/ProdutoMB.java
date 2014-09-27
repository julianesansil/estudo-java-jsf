package br.curso.jsf2.control.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.curso.jsf2.model.bean.Produto;
import br.curso.jsf2.model.dao.GenericDAO;

@ViewScoped
@ManagedBean
public class ProdutoMB {
	
	private Produto produto = new Produto();
	private List<Produto> produtos;

	@PostConstruct
	public void carregarProdutos() {
		produtos = new GenericDAO<Produto>(Produto.class).listarTodos();
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		System.out.println("acesso ao banco");
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void salvar() {
		GenericDAO<Produto> dao = new GenericDAO<Produto>(Produto.class);
		
		if (produto.getId() == null)
			dao.cadastrar(produto);
		else dao.atualizar(produto);
		
		this.produto = new Produto();
		this.produtos = dao.listarTodos();
	}
	
	public void excluir() {
		GenericDAO<Produto> dao = new GenericDAO<Produto>(Produto.class);
		dao.excluir(this.produto);
		this.produto = new Produto();
		
		this.produtos = dao.listarTodos();
	}

}
