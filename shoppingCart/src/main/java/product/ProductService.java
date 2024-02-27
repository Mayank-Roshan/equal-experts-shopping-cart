package product;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    public Mono<ProductInfo> getProductInfo(String name){

        return WebClient
                .create("https://equalexperts.github.io/")
                .get()
                .uri("/backend-take-home-test-data/"+name+".json")
                .retrieve()
                .bodyToMono(ProductInfo.class);
//                .onErrorReturn(null);
    }
}
