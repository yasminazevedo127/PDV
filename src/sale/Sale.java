package sale;

import java.util.ArrayList;
import java.util.List;

import product.Product;

public abstract class Sale {
    protected List<SaleItem> items = new ArrayList<>();
	
	private static int sequentialId;
	
	protected int id;
	

    public Sale() {
        this.id = ++sequentialId;
    }
	
	public abstract SaleType getType();
	
	public boolean addItem(Product product, int quantity) {
		if (product.removeStock(quantity)) {
            items.add(new SaleItem(product, quantity));
            return true;
        } else {
        	return false;
        }
	}
	
	public double getTotal() {
        return items.stream().mapToDouble(SaleItem::getSubtotal).sum();
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Venda #").append(id).append(" (").append(getType()).append(")\n");
        for (SaleItem item : items) {
            builder.append(" - ").append(item.getProduct().getName())
              .append(" x ").append(item.getQuantity())
              .append(" = R$").append(item.getSubtotal()).append("\n");
        }
        builder.append("Total: R$").append(getTotal()).append("\n");
        return builder.toString();
    }
}
