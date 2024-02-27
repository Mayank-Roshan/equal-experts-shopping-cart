package cart;

import org.junit.jupiter.api.Test;
import product.Product;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void shouldAddNewProductsToMapIfTheyDoNotExist(){
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
    void shouldIncreaseQuantityOfExistingProductsInMap(){
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

    @Test
    void shouldReturnSubTotalPriceAs15_02For2CornflakesAnd1Weetabix(){
        ShoppingCart cart = new ShoppingCart();
        var cornflakes = new Product("Corn Flakes",2.52);
        var weetabix = new Product("Weetabix",9.98);
        cart.addProduct("cornflakes",cornflakes);
        cart.addProduct("cornflakes",cornflakes);
        cart.addProduct("weetabix",weetabix);
        assertEquals(2,cart.getProducts().size());
        cart.calculateCartValues();
        var subTotalPrice = cart.getSubTotalPrice();
        assertEquals(15.02,subTotalPrice);
    }

    @Test
    void shouldReturn1_88TaxForTwoCornFlakesAndOneWeetabix(){
        ShoppingCart cart = new ShoppingCart();
        var cornflakes = new Product("Corn Flakes",2.52);
        var weetabix = new Product("Weetabix",9.98);
        cart.addProduct("cornflakes",cornflakes);
        cart.addProduct("cornflakes",cornflakes);
        cart.addProduct("weetabix",weetabix);
        cart.calculateCartValues();
        var tax = cart.getTax();
        assertEquals(1.88,tax);
    }
    @Test
    void shouldReturn16_90TotalPayableForTwoCornFlakesAndOneWeetabix(){
        ShoppingCart cart = new ShoppingCart();
        var cornflakes = new Product("Corn Flakes",2.52);
        var weetabix = new Product("Weetabix",9.98);
        cart.addProduct("cornflakes",cornflakes);
        cart.addProduct("cornflakes",cornflakes);
        cart.addProduct("weetabix",weetabix);
        cart.calculateCartValues();
        var totalPayable = cart.getTotalPayable();
        assertEquals(16.90,totalPayable);
    }

    @Test
    void shoudlReturnExpectedOutPutForStubbedInput(){
        String expectedSummary = "Cart contains 2*Corn Flakes @2.52\n" +
                "Cart contains 1*Weetabix @9.98\n" +
                "SubTotal = 15.02\n" +
                "Tax = 1.88\n" +
                "Total = 16.9\n";
        ShoppingCart cart = new ShoppingCart();
        var cornflakes = new Product("Corn Flakes",2.52);
        var weetabix = new Product("Weetabix",9.98);
        cart.addProduct("cornflakes",cornflakes);
        cart.addProduct("cornflakes",cornflakes);
        cart.addProduct("weetabix",weetabix);
        cart.calculateCartValues();
        assertEquals(expectedSummary,cart.getSummary());
    }

}