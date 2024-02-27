import cart.ShoppingCart;
import product.Product;
import product.ProductService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class shoppingCartApplication {

    public static void main(String[] args) {
        var productNames = List.of("corn flakes","cornflakes","cornflakes","cornflakes","weetabix","weetabix","weetabix");
        var shoppingCart = addProductsToCart(productNames);


    }

    public static ShoppingCart addProductsToCart(List<String> productNames){
        ProductService productService = new ProductService();
        ShoppingCart shoppingCart = new ShoppingCart();
//        var cheeriosInfo = productService.getProductUnitPrice("cheerios").block();
//        Product cheerios = new Product(cheeriosInfo.getTitle(),cheeriosInfo.getPrice());

        for(String productName:productNames){
            if(shoppingCart.isProductPresent(productName)){
                shoppingCart.addProduct(productName,shoppingCart.getProducts().get(productName));
                continue;
            }
            var productInfo = productService.getProductInfo(productName).block();
            if (productInfo == null) continue;
            shoppingCart.addProduct(productName,new Product(productInfo.getTitle(),productInfo.getPrice()));
        }
        return shoppingCart;
    }
}
