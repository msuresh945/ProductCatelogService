package com.example.productcatelogservice.services;

import com.example.productcatelogservice.dtos.fakestore.FakeStoreProductRequestDto;
import com.example.productcatelogservice.dtos.fakestore.FakeStoreProductResponseDto;
import com.example.productcatelogservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FakeStoreProductService implements IProductService{

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }


    public Product getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> fakeStoreProductResponseDto = restTemplate.getForEntity("https://fakestoreapi.com/products/"+id, FakeStoreProductResponseDto.class);
        if(fakeStoreProductResponseDto.getBody()!=null && fakeStoreProductResponseDto.getStatusCode().is2xxSuccessful()){
            return FakeStoreProductResponseDto.to(fakeStoreProductResponseDto.getBody());
        }
        return null;
    }

    public void deleteProduct(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }

    public Product createProduct(Product product){
        FakeStoreProductRequestDto request = FakeStoreProductRequestDto.from(product);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> fakeStoreProductResponseDto =restTemplate.postForEntity("https://fakestoreapi.com/products", request, FakeStoreProductResponseDto.class);
        assert fakeStoreProductResponseDto.getBody() != null;
        return FakeStoreProductResponseDto.to(fakeStoreProductResponseDto.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto[]> fakeStoreProductResponseDto = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductResponseDto[].class);

        List<Product> products = new ArrayList<Product>();
        for(FakeStoreProductResponseDto fakeStoreProductResponseDto1: Objects.requireNonNull(fakeStoreProductResponseDto.getBody())){
            products.add(FakeStoreProductResponseDto.to(fakeStoreProductResponseDto1));
        }
        return products;

//        FakeStoreProductResponseDto[] responseArray = fakeStoreProductResponseDto.getBody();
//        if (responseArray != null) {
//            return Stream.of(responseArray)
//                    .map(FakeStoreProductResponseDto::to)
//                    .collect(Collectors.toList());
//        }
//        return Collections.emptyList();
    }

    public Product updateProduct(Long id, Product product){
        FakeStoreProductRequestDto request = FakeStoreProductRequestDto.from(product);
        ResponseEntity<FakeStoreProductResponseDto> response=requestForEntity("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, request, FakeStoreProductResponseDto.class);
        return FakeStoreProductResponseDto.to(Objects.requireNonNull(response.getBody()));

    }

    public <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate =  restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


}
