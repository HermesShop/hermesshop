package br.edu.unitri.pshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "product_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 813440582621834761L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_date")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date date;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client")
	@NotNull
	private Client client;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
	private Set<ItemOrder> itens = new HashSet<ItemOrder>();

	@Column(name = "total")
	@NotNull
	private Double totalValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<ItemOrder> getItens() {
		if (itens == null) {
			itens = new HashSet<ItemOrder>();
		}
		return itens;
	}

	public void setItens(Set<ItemOrder> itens) {
		this.itens = itens;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public List<ItemOrder> getItensInOrderedList() {
		return new ArrayList<ItemOrder>(getItens());
	}

	public void adicionarItem(Product product, Integer quantity) {
		ItemOrder hasItem = getItem(product);
		if (hasItem != null) {
			updateQuantity(product, hasItem.getQuantity() + quantity);
		} else {
			getItens().add(new ItemOrder(product, quantity));
			calculateTotal();
		}
	}

	public void removerItem(Product produto) {
		getItens().remove(new ItemOrder(produto));
		calculateTotal();
	}

	public ItemOrder getItem(Product produto) {
		ItemOrder itemAProcurar = new ItemOrder(produto);
		for (ItemOrder item : getItens()) {
			if (item.equals(itemAProcurar)) {
				return item;
			}
		}
		return null;
	}

	public void updateQuantity(Product product, Integer newQuantity) {
		ItemOrder item = getItem(product);
		if (item == null) {
			throw new IllegalArgumentException(
					"Item nao encontrado para produto " + product);
		}
		item.updateQuantity(newQuantity);
		calculateTotal();
	}

	public void calculateTotal() {
		totalValue = 0D;
		for (ItemOrder item : getItens()) {
			totalValue += item.getTotalPrize();
		}
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", itens=" + itens
				+ ", totalValue=" + totalValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result
				+ ((totalValue == null) ? 0 : totalValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		if (totalValue == null) {
			if (other.totalValue != null)
				return false;
		} else if (!totalValue.equals(other.totalValue))
			return false;
		return true;
	}
}