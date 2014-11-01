package br.curso.jsf2.control.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.curso.jsf2.model.bean.Item;
import br.curso.jsf2.model.bean.NotaFiscal;
import br.curso.jsf2.model.bean.Produto;
import br.curso.jsf2.model.dao.GenericDAO;

@ManagedBean
public class NotaFiscalMB {
	private NotaFiscal notaFiscal = new NotaFiscal();
	private Long idProduto;
	private Item item = new Item();
	
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public void adicionarItem() {
		GenericDAO<Produto> dao = new GenericDAO<Produto>(Produto.class);
		
		Produto produto = dao.buscarPorId(idProduto);
		item.setProduto(produto);
		item.setPreco(produto.getPreco());
		
		item.setNotaFiscal(notaFiscal);
		notaFiscal.adicionarItem(item);
		
		item = new Item();
	}
	
	public void adicionar() {
		GenericDAO<NotaFiscal> notaFiscalDAO = new GenericDAO<NotaFiscal>(NotaFiscal.class);
		GenericDAO<Item> itemDAO = new GenericDAO<Item>(Item.class);
		
		notaFiscalDAO.cadastrar(this.notaFiscal);
		for (Item item : notaFiscal.getItens()) {
			itemDAO.cadastrar(item);
		}
		
		this.notaFiscal = new NotaFiscal();
	}
}
