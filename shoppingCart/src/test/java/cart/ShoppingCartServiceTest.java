package cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    ShoppingCartService shoppingCartService;

    @BeforeEach
    public void setUp(){
        shoppingCartService = new ShoppingCartService();
    }


}