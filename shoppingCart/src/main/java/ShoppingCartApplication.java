import cart.ShoppingCartService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ShoppingCartApplication {

    public static void main(String[] args) {
        //Uncomment different list for different results
//        var productNames = List.of("cornflakes","cornflakes","cheerios","shreddies"
//                ,"cornflakes","weetabix","frosties","frosties","weetabix");

        var productNames = List.of("cornflakes","cornflakes","weetabix");
        ShoppingCartService shoppingCartService= new ShoppingCartService();
        var shoppingCart = shoppingCartService.enqueueProductsForCart(productNames);
        shoppingCart.calculateCartValues();
        System.out.println();
        System.out.println(shoppingCart.getSummary());
    }
}
