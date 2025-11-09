package sale;

import java.util.ArrayList;

import product.Product;

public class SaleRequest {
    SaleType type;
    String address;
    ArrayList<Product> wishList;
    ArrayList<Integer> qntList;

    SaleRequest(SaleType t, String add, ArrayList<Product> wl, ArrayList<Integer> ql) {
        type = t;
        address = add;
        wishList = wl;
        qntList = ql;
    }
}
