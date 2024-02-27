package cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import product.ProductInfo;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    ShoppingCartService shoppingCartService;

    private MockWebServer mockWebTestServer;
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws IOException {
        shoppingCartService = new ShoppingCartService();
        mockWebTestServer = new MockWebServer();
        mockWebTestServer.close();
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void destroy() throws IOException {
        mockWebTestServer.close();
    }

    @Test
    void shouldreturnShoppingCartWithOneProductHavingTwoQuantity() throws JsonProcessingException {
        ProductInfo mockWeetabix = new ProductInfo("weetabix",9.98);
        mockWebTestServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockWeetabix))
                .addHeader("Content-Type", "application/json"));

        var result = shoppingCartService.enqueueProductsForCart(List.of("weetabix","weetabix"));
        assertEquals(1,result.getProducts().size());
        assertEquals(2,result.getProducts().get("weetabix").getQuantity());
        assertEquals(9.98,result.getProducts().get("weetabix").getUnitPrice());
    }

}