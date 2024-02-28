package product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.io.IOException;

class ProductServiceTest {

    private MockWebServer mockWebTestServer;

    String baseUrl;
    ObjectMapper objectMapper;

    ProductService productService;

    @BeforeEach
    void setUp() throws IOException {
        mockWebTestServer = new MockWebServer();
        mockWebTestServer.start();
        objectMapper = new ObjectMapper();
        productService = new ProductService();
        baseUrl = "http://localhost:"+mockWebTestServer.getPort()+"/dummyDomain";
    }

    @AfterEach
    void destroy() throws IOException {
        mockWebTestServer.shutdown();
    }

    @Test
    void shouldFetchDataFromProductApi() throws  JsonProcessingException {
        ProductInfo mockWeetabix = new ProductInfo("Cheerios",9.98);
        mockWebTestServer.enqueue(new MockResponse().setResponseCode(200)
                .setBody(objectMapper.writeValueAsString(mockWeetabix))
                .addHeader("Content-Type", "application/json"));
        StepVerifier.create(productService.getProductInfo(baseUrl,"weetabix"))
                .consumeNextWith(product -> {
                    Assertions.assertEquals("Cheerios",product.getTitle());
                    Assertions.assertEquals(9.98,product.getPrice());
                })
                .verifyComplete();
    }
    @Test
    void shouldReturnEmptyProductInfoDataFromProductApi() throws  JsonProcessingException {
        mockWebTestServer.enqueue(new MockResponse().setResponseCode(404));
        StepVerifier.create(productService.getProductInfo(baseUrl,"corn flakes"))
                .consumeNextWith(product -> {
                    Assertions.assertEquals(-1,product.getPrice());
                })
                .verifyComplete();
    }

}