package br.edu.unitri.pshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class ItemOrder implements Serializable, Comparable<ItemOrder> {
	private static final long serialVersionUID = 7848426935997596400L;

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
}