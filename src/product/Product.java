package product;

public class Product {
	private String name;
	private int code;
	private double price;
	private int stock;
	

	public Product(String name, int code, double price) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.code = code;
		this.price = price;
		this.stock = 0;
	}


	public int getStock() {
		return stock;
	}


	public void addStock(int quantity) {
		this.stock += quantity;
	}
	
	public boolean removeStock(int quantity) {
        if (this.stock >= quantity) {
            this.stock -= quantity;
            return true;
        }
        return false;
    }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getCode() {
		return code;
	}


	public void setCode(float code) {
		this.code = code;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
	    return code + " - " + name + " | R$" + price + " | Stock: " + stock;
	}
}
