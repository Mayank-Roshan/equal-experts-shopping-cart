package cart;

import org.junit.jupiter.api.Test;
import product.Product;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void shouldAddNewProductsToMap(){
        ShoppingCart cart = new ShoppingCart();
        var cheerios = new Product("Cheerios",8.43);
        var weetabix = new Product("weetabix",9.98);
        cart.addProduct("Cheerios",cheerios);
        cart.addProduct("weetabix",weetabix);
        assertEquals(2,cart.getProducts().size());
        var resultCheerios = cart.getProducts().get(cheerios.getName());
        var resultWeetabix = cart.getProducts().get(weetabix.getName());
        assertEquals(1,resultCheerios.getQuantity());
        assertEquals(cheerios.getName(),resultCheerios.getName());
        assertEquals(1,resultWeetabix.getQuantity());
        assertEquals(weetabix.getName(),resultWeetabix.getName());
    }


    @Test
    void shouldNotAddExistingProductsToMap(){
        ShoppingCart cart = new ShoppingCart();
        var cheerios = new Product("Cheerios",8.43);
        var weetabix = new Product("weetabix",9.98);
        cart.addProduct("Cheerios",cheerios);
        cart.addProduct("weetabix",weetabix);
        cart.addProduct("weetabix",weetabix);
        assertEquals(2,cart.getProducts().size());
        var resultCheerios = cart.getProducts().get(cheerios.getName());
        var resultWeetabix = cart.getProducts().get(weetabix.getName());
        assertEquals(1,resultCheerios.getQuantity());
        assertEquals(cheerios.getName(),resultCheerios.getName());
        assertEquals(cheerios.getUnitPrice(),resultCheerios.getUnitPrice());
        assertEquals(2,resultWeetabix.getQuantity());
        assertEquals(weetabix.getName(),resultWeetabix.getName());
    }

}