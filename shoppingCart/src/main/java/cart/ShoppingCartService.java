package cart;

import org.springframework.stereotype.Service;
import product.Product;
import product.ProductConstants;
import product.ProductService;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ProductService productService;

    public ShoppingCartService() {
        productService = new ProductService();
    }

    public ShoppingCart enqueueProductsForCart(List<String> productNames){
        ShoppingCart shoppingCart = new ShoppingCart();
        for(String productName:productNames){
            if(shoppingCart.isProductPresent(productName)){
                shoppingCart.addProduct(productName,shoppingCart.getProducts().get(productName));
                continue;
            }
            var productInfo = productService.getProductInfo(ProductConstants.EQUAL_EXPERTS_BASE_URL,productName).block();
            if (productInfo == null || productInfo.getPrice()==-1) {
                System.out.println("Info : productName " + productName + " was not found or is incorrectly spelt");
                continue;
            }
            shoppingCart.addProduct(productName,new Product(productInfo.getTitle(),productInfo.getPrice()));
        }
        return shoppingCart;
    }
}
