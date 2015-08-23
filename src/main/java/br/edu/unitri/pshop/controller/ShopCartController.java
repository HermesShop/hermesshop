package br.edu.unitri.pshop.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.pshop.exception.ClientNotFoundException;
import br.edu.unitri.pshop.model.Client;
import br.edu.unitri.pshop.model.ItemOrder;
import br.edu.unitri.pshop.model.Order;
import br.edu.unitri.pshop.model.Product;
import br.edu.unitri.pshop.service.ClientRegistration;
import br.edu.unitri.pshop.service.OrderRegistration;

@Named
@SessionScoped
public class ShopCartController implements Serializable {
	private static final long serialVersionUID = 1172514592649335124L;

	private Order shopcarOrder;
	private Long idPedidoGerado;
	private Client client;
	private Product removeProduct;
	
	@Inject
	private ClientRegistration clientService;
	@Inject
	private OrderRegistration orderService;
	
	@PostConstruct
	public void init() {
		shopcarOrder = new Order();
		client = new Client();
	}

	public String adicionarItem(Product produto) {
		shopcarOrder.adicionarItem(produto, 1);
		return "carrinho?faces-redirect=true";
	}

	public void removerItem() {
		shopcarOrder.removerItem(removeProduct);
	}

	public void atualizarQuantidadeItem(Product produto, Integer novaQuantidade) {
		shopcarOrder.updateQuantity(produto, novaQuantidade);
	}

	public String fecharPedidoUsuarioExistente() {
		try {
			return fecharPedido();
		} catch (ClientNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String fecharPedidoNovoUsuario() throws Exception {
		client = clientService.add(client);
		try {
			return fecharPedido();
		} catch (ClientNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String fecharPedido() throws ClientNotFoundException {
		shopcarOrder = orderService.addOrder(shopcarOrder,
				client.getEmail(), client.getPassword());
		idPedidoGerado = shopcarOrder.getId();
		init();
		return "pedidoFechado?faces-redirect=true";
	}

	public void recalcularTotal(ItemOrder itemOrder) {
		itemOrder.calculatesTotal();
		shopcarOrder.calculateTotal();
	}

	public boolean temItens() {
		return shopcarOrder.getItens().size() > 0;
	}

	public Client getClient() {
		return client;
	}

	public Long getIdPedidoGerado() {
		return idPedidoGerado;
	}

	public Order getShopcarOrder() {
		return shopcarOrder;
	}

	public Product getProdutoRemover() {
		return removeProduct;
	}

	public void setProdutoRemover(Product removeProduct) {
		this.removeProduct = removeProduct;
	}
}
