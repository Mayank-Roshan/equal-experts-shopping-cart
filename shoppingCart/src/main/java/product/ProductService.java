package product;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    public Mono<ProductInfo> getProductInfo(String baseUrl,String name){

        return WebClient
                .create(baseUrl)
                .get()
                .uri("/backend-take-home-test-data/"+name+".json")
                .retrieve()
                .bodyToMono(ProductInfo.class)
                .onErrorReturn(new ProductInfo("",-1) );
    }
}
