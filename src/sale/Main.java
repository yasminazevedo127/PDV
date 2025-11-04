package sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import product.Product;
import product.ProductRepository;

public class Main {
	private List<Sale> sales = new ArrayList<>();
	private static ProductRepository products = new ProductRepository();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		while(true) {
	        System.out.println("Digite a opção que deseja: ");
	        System.out.println("1- registrar produto");
	        System.out.println("2- adicionar produto no estoque");
	        System.out.println("3- listar produtos");
	        System.out.println("4- sair");

	        opcao = Integer.parseInt(scanner.nextLine());
	        if(opcao == 1) {
	        	System.out.println("Digite o nome do produto:");
	        	String nome = scanner.nextLine();
	        	System.out.println("Digite o codigo do produto");
	        	int code = Integer.parseInt(scanner.nextLine());
	        	System.out.println("Digite o preço do produto");
	        	double price = Double.parseDouble(scanner.nextLine());
	        	
	        	products.registerProduct(nome, code, price);
	        	System.out.println("");
	        }
	        else if(opcao == 2) {
	        	System.out.println("Digite o codigo do produto");
	        	int code = scanner.nextInt();
	        	System.out.println("Digite o preço do produto");
	        	int quantity = scanner.nextInt();
	        	
	        	products.addStock(code, quantity);
	        	System.out.println("");
	        }
	        else if(opcao == 3) {
	    		products.listProducts();
	    		System.out.println("");
	        }
	        else if(opcao == 4) {
	        	scanner.close();
	        	System.exit(0);
	        }
	        else {
	        	System.out.println("Escolha uma opção existente");
	        }
	       
		}

	}

	
	 
	public void registerSale(SaleType type, String address, Map<Integer, Integer> itemsMap) {
	    Sale sale = (type == SaleType.STORE) ? new Store() : new Web(address);

	    for (Map.Entry<Integer, Integer> entry : itemsMap.entrySet()) {
            Product p = products.findProduct(entry.getKey());
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
