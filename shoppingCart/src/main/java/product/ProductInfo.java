package product;

public class ProductInfo {


    private final String title;

    private final double price;

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
