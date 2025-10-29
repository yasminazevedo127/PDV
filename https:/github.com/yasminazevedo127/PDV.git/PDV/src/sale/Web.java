package sale;

public class Web extends Sale{
	private String deliveryAdress;

	public Web(String deliveryAdress) {
		// TODO Auto-generated constructor stub
		super();
		this.deliveryAdress = deliveryAdress;
	}

	public String getDeliveryAdress() {
		return deliveryAdress;
	}

	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}

	@Override
	public SaleType getType() {
		// TODO Auto-generated method stub
		return SaleType.WEB;
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venda #").append(id).append(" (").append(getType()).append(")\n");
        sb.append("Endereço: ").append(deliveryAdress).append("\n");
        for (SaleItem item : items) {
            sb.append(" - ").append(item.getProduct().getName())
              .append(" x ").append(item.getQuantity())
              .append(" = R$").append(item.getSubtotal()).append("\n");
        }
        sb.append("Total: R$").append(getTotal()).append("\n");
        return sb.toString();
    }
}
