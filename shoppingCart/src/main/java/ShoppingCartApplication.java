import cart.ShoppingCartService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"cart.ShoppingCartService"})
@ConfigurationPropertiesScan
public class ShoppingCartApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class,args).close();
    }

    @Override
    public void run(ApplicationArguments args) {
        //Uncomment different list for different results
        var productNames = List.of("cornflakes","cornflakes","cheerios","shreddies"
                ,"cornflakes","weetabix","frosties","frosties","weetabix");

//        var productNames = List.of("cornflakes","cornflakes","weetabix");
        ShoppingCartService shoppingCartService= new ShoppingCartService();
        var shoppingCart = shoppingCartService.enqueueProductsForCart(productNames);
        shoppingCart.calculateCartValues();
        System.out.println(shoppingCart.getSummary());
    }
}
