package br.edu.unitri.pshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_order")
public class ItemOrder implements Serializable, Comparable<ItemOrder> {
	private static final long serialVersionUID = 7848426935997596400L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;

	@NotNull
	@Column(name = "unit_prize")
	private Double unitPrize;

	@NotNull
	private Integer quantity;

	@NotNull
	@Column(name = "total_prize")
	private Double totalPrize;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_order", referencedColumnName = "id")
	private Order order;

	public ItemOrder() {
	}

	public ItemOrder(Product product) {
		this.product = product;
	}

	public ItemOrder(Product product, Integer quantity) {
		this.product = product;
		this.unitPrize = product.getPrize();
		this.quantity = quantity;
		calculatesTotal();
	}

	public void calculatesTotal() {
		totalPrize = unitPrize * quantity;
	}

	public void updateQuantity(Integer newQuantity) {
		this.quantity = newQuantity;
		calculatesTotal();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getUnitPrize() {
		return unitPrize;
	}

	public void setUnitPrize(Double unitPrize) {
		this.unitPrize = unitPrize;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrize() {
		return totalPrize;
	}

	public void setTotalPrize(Double totalPrize) {
		this.totalPrize = totalPrize;
	}

	public int compareTo(ItemOrder o) {
		return product.getTitle().compareTo(o.getProduct().getTitle());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "ItemOrder [id=" + id + ", product=" + product + ", unitPrize="
				+ unitPrize + ", quantity=" + quantity + ", totalPrize="
				+ totalPrize + ", order=" + order + "]";
	}

}