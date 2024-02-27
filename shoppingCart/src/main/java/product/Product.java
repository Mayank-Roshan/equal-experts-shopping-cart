package product;


public class Product {

    private ProductService productService;
    private String name;
    private int quantity;

    private double unitPrice;

    public Product(String name,double unitPrice) {
        this.name = name;
        this.quantity = 1;
        this.unitPrice = unitPrice;
    }

    public String getName(){
        return this.name;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void increaseQuantityByOneUnit(){
        this.quantity++;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
