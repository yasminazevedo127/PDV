package sale;
import product.*;

import java.util.ArrayList;
import java.util.List;

public class SaleRepository {
    private List<Sale> sales = new ArrayList<>();

    public boolean isEmpty() {
        return this.sales.isEmpty();
    }

    public void registerSale(SaleRequest saleReq, ProductRepository products) {
        Sale sale = (saleReq.type == SaleType.STORE) ? new Store() : new Web(saleReq.address);

        ArrayList<Product> wishList = saleReq.wishList;
        ArrayList<Integer> qntList = saleReq.qntList;

        for (int i = 0; i < wishList.size(); ++i) {
            Product produtoEscolhido = products.findProduct(wishList.get(i).getCode());
            if (produtoEscolhido.getStock() - qntList.get(i) >= 0) {
                sale.addItem(produtoEscolhido, qntList.get(i));
                produtoEscolhido.removeStock(0);
                continue;
            } else {
                System.out.println("\nEste item não está em estoque na quantidade exigida: " + wishList.get(i).getName() + " - x" + qntList.get(i) + '\n');
                continue;
            }
        }

        sales.add(sale);
        System.out.println("Venda efetuada!");
    }

    public void listSales() {
        if (sales.isEmpty()) {
            System.out.println("No sales registered.");
            return;
        }
        for (Sale s : sales) {
            System.out.println(s.id + ":\n");
            for (SaleItem si : s.getItems()) {
                Product p = si.getProduct();
                System.out.println("\t " + p.getName() + " | R$: " + p.getPrice() + " | Qnt: " + si.getQuantity() + "\n");
            }
        }
    }

    public int totalItemsSold() {
        if (sales.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (Sale s : sales) {
            total += s.getItems().size();
        }
        return total;
    }
    public double totalPrice() {
        if (sales.isEmpty()) {
            return 0.00;
        }
        double total = 0.00;
        for (Sale s : sales) {
            total += s.getTotal();
        }
        return total;
    }

    protected class Tuple<X, Y> {
        final X left; 
        final Y right; 
        Tuple(X left, Y right) { 
            this.left = left; 
            this.right = right; 
        } 
    };

    private List<Tuple<Product, Integer>> filterAmongSales(List<Tuple<Product, Integer>> allProducts) {
        List<Tuple<Product, Integer>> filteredProducts = new ArrayList<>();
        for (int i = allProducts.size() - 1 ; i >= 0; --i) {
            Tuple<Product, Integer> p = allProducts.get(i);

            int index = -1;
            for (int j = 0; j < filteredProducts.size(); ++j) {
                if (filteredProducts.get(j).left.getCode() == p.left.getCode()) {
                    index = j;
                    break;
                }
            }

            if (index == -1) {
                filteredProducts.add(p);
            }
            else {
                filteredProducts
                    .set( index, new Tuple<Product, Integer>( filteredProducts.get(index).left, filteredProducts.get(index).right + p.right) );
            }
        }

        return filteredProducts;
    }

    public void listInfoForEachSale() {
        List<Tuple<Product, Integer>> allProducts = new ArrayList<>();

        for (Sale s : sales) {
            for (SaleItem p : s.items) {
                allProducts.add(new Tuple<Product, Integer>(p.getProduct(), p.getQuantity()));
            }
        }

        allProducts.sort((a, b) -> Integer.compare(a.right, b.right));

        List<Tuple<Product, Integer>> filteredProducts = filterAmongSales(allProducts);

        for (Tuple<Product,Integer> t : filteredProducts) {
            System.out.println('\t' + t.left.getName() + " | R$: " + t.left.getPrice() + " | Qnt:" + t.right + '\n');
        }
    }

    public Sale findSale(int id) {
        return sales.stream()
        .filter(s -> s.getId() == id)
        .findFirst()
        .orElse(null);
    }
}
