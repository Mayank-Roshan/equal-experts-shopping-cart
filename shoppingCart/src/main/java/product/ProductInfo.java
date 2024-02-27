package product;

public class ProductInfo {


    private String title;

    public ProductInfo(String title, double price) {
        this.title = title;
        this.price = price;
    }

    private double price;

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}
