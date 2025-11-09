package product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
	private List<Product> products = new ArrayList<>();

	public boolean isEmpty() {
		return this.products.isEmpty();
	}
	
	public Product findProduct(int code) {
        return products.stream()
                .filter(p -> p.getCode() == code)
                .findFirst()
                .orElse(null);
    }
	
	public void registerProduct(String name, int code, double price) {
	    try {
	        if (findProduct(code) != null)
                throw new IllegalStateException("Product code already exists: " + code);
	        if (price <= 0) 
				throw new IllegalArgumentException("Price must be positive");
				
	        products.add(new Product(name, code, price));
            System.out.println("Product registered successfully.");
	    } catch (IllegalArgumentException | IllegalStateException e) {
	    	System.out.println("⚠️ " + e.getMessage());
        }
    }
	
	public void addStock(int code, int quantity) {
        try {
            Product p = findProduct(code);

            if (p == null) 
				throw new IllegalArgumentException("Product not found: " + code);
            if (quantity <= 0) 
				throw new IllegalArgumentException("Quantity must be positive");

            p.addStock(quantity);
            System.out.println("Stock updated successfully.");
        } catch (IllegalArgumentException e) {
        	System.out.println("⚠️ " + e.getMessage());
        }
    }
	
	public void listProducts() {
	    if (products.isEmpty()) {
	        System.out.println("No products registered.");
            return;
        }
       for (Product p : products) System.out.println("\t" + p);
    }
}
