package cart;

import product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;


public class ShoppingCart {
    private HashMap<String,Product> products;
    private double subTotalPrice;
    private double tax;
    private double totalPayable;
    private String summary;

    public ShoppingCart() {
        products = new HashMap<>();
        tax=0.0;
        subTotalPrice=0.0;
        totalPayable=0.0;
        summary="";
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public double getSubTotalPrice(){
        return  this.subTotalPrice;
    }

    public double getTax(){
        return this.tax;
    }

    public double getTotalPayable(){
        return this.totalPayable;
    }

    public void addProduct(String productKey,Product product){
        if(isProductPresent(productKey)){
            var existing = products.get(productKey);
            existing.increaseQuantityByOneUnit();
            return;
        }
        products.put(productKey,product);
    }

    public boolean isProductPresent(String name){
        return products.containsKey(name);
    }

    public void calculateCartValues(){
        double subTotal = 0;
        for(Map.Entry<String,Product> mapEntry:products.entrySet()){
            var price = mapEntry.getValue().getUnitPrice();
            var quantity = mapEntry.getValue().getQuantity();
            summary = summary + "Cart contains " + quantity + "*" + mapEntry.getValue().getName() + " @" + price +"\n";
            subTotal+=price*quantity;
        }
        this.subTotalPrice= roundOffToTwoDecimalPlaces(subTotal);
        summary = summary + "SubTotal = " + subTotal + "\n";
        this.tax= roundOffToTwoDecimalPlaces(CartConstants.TAX_RATE*this.subTotalPrice/100.0);
        summary = summary + "Tax = " + tax + "\n";
        this.totalPayable=roundOffToTwoDecimalPlaces(this.subTotalPrice+this.tax);
        summary = summary + "Total = " + totalPayable + "\n";
    }

    private double roundOffToTwoDecimalPlaces(double number){
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public String getSummary() {
        return summary;
    }
}
