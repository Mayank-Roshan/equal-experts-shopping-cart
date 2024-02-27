package cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.Product;
import product.ProductService;

import java.util.List;

@Service
public class ShoppingCartService {

    private ProductService productService;

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
            var productInfo = productService.getProductInfo(productName).block();
            if (productInfo == null) continue;
            shoppingCart.addProduct(productName,new Product(productInfo.getTitle(),productInfo.getPrice()));
        }
        return shoppingCart;
    }
}
