package sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import product.Product;

public class Main {
	private List<Product> products = new ArrayList<>();
	private List<Sale> sales = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	            if (price <= 0) throw new IllegalArgumentException("Price must be positive");

	            products.add(new Product(name, code, price));
	            System.out.println("Product registered successfully.");
	        } catch (IllegalArgumentException | IllegalStateException e) {
	            System.out.println("⚠️ " + e.getMessage());
	        }
	    }
	 
	 public void listProducts() {
	        if (products.isEmpty()) {
	            System.out.println("No products registered.");
	            return;
	        }
	        for (Product p : products) System.out.println(p);
	    }
	 
	 public void addStock(int code, int quantity) {
	        try {
	            Product p = findProduct(code);
	            if (p == null) throw new IllegalArgumentException("Product not found: " + code);
	            if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");

	            p.addStock(quantity);
	            System.out.println("Stock updated successfully.");
	        } catch (IllegalArgumentException e) {
	            System.out.println("⚠️ " + e.getMessage());
	        }
	    }
	 
	 public void registerSale(SaleType type, String address, Map<Integer, Integer> itemsMap) {
	        Sale sale = (type == SaleType.STORE) ? new Store() : new Web(address);

	        for (Map.Entry<Integer, Integer> entry : itemsMap.entrySet()) {
	            Product p = findProduct(entry.getKey());
	            int quantity = entry.getValue();

	            try {
	                if (p == null)
	                    throw new IllegalArgumentException("Product not found: " + entry.getKey());
	                if (!p.removeStock(quantity))
	                    throw new IllegalStateException("Insufficient stock for " + p.getName());

	                sale.addItem(p, quantity);

	            } catch (IllegalArgumentException | IllegalStateException e) {
	                System.out.println("⚠️ " + e.getMessage());
	            }
	        }

	        if (!sale.getItems().isEmpty()) {
	            sales.add(sale);
	            System.out.println("Sale registered successfully!");
	            System.out.println(sale); 
	        } else {
	            System.out.println("No valid items in sale. Sale not registered.");
	        }
	    }
	
	 public void listSales() {
	        if (sales.isEmpty()) {
	            System.out.println("No sales registered.");
	            return;
	        }
	        for (Sale s : sales) System.out.println(s + "\n");
	    }
}
