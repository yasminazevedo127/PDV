package sale;

import product.Product;

public class SaleItem {
	private Product product;
	private Integer quantity;
	
	public SaleItem(Product produto, int quantidade) {
		// TODO Auto-generated constructor stub
		this.product = produto;
		this.quantity = quantidade;
	}

	public Product getProduct() {
		return product;
	}

	public double getSubtotal() {
		return product.getPrice() * quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
