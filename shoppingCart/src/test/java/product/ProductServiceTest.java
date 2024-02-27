package product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.io.IOException;

class ProductServiceTest {

    private MockWebServer mockWebTestServer;
    ObjectMapper objectMapper;

    ProductService productService;

    @BeforeEach
    void setUp() throws IOException {
        mockWebTestServer = new MockWebServer();
        mockWebTestServer.start();
        objectMapper = new ObjectMapper();
        productService = new ProductService();
    }


    @Test
    void shouldFetchDataFromProductApi() throws JSONException, JsonProcessingException {
        ProductInfo mockWeetabix = new ProductInfo("Weetabix",9.98);
        mockWebTestServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockWeetabix))
                .addHeader("Content-Type", "application/json"));

        StepVerifier.create(productService.getProductInfo("weetabix"))
                .consumeNextWith(product -> {
                    Assertions.assertEquals("Weetabix",product.getTitle());
                    Assertions.assertEquals(9.98,product.getPrice());

                })
                .verifyComplete();
    }

}