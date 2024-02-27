package cart;

import lombok.Getter;
import product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ShoppingCart {
    private HashMap<String,Product> products;

    public ShoppingCart() {
        products = new HashMap<>();
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }


    public void addProduct(String productKey,Product product){
        if(isProductPresent(productKey)){
            var existing = products.get(productKey);
            existing.increaseQuantityByOneUnit();
            return;
        }
        products.put(productKey,product);
    }

    public double getTaxPayable(){
        return 0;
    }

    public double getTotalPayable(){
        return 0;
    }

    public boolean isProductPresent(String name){
        return products.containsKey(name);
    }

}
