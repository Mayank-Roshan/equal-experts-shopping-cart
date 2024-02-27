package product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
