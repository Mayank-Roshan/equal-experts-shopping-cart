package product;

public class ProductInfo {


    private String title;

    private double price;

    public ProductInfo(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}
