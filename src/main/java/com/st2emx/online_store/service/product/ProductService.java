package com.st2emx.online_store.service.product;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.dto.product.ProductCommentCreateDto;
import com.st2emx.online_store.dto.product.ProductDto;
import com.st2emx.online_store.dto.product.ProductFullDto;
import com.st2emx.online_store.dto.shop.FilterDto;
import com.st2emx.online_store.service.BaseService;
import org.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ProductService implements BaseService {

    public ProductFullDto getProductFull(Long id) {
        String url = "http://localhost:8080/api/v1/product/full/"+id;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });

        Type type = new TypeToken<ProductFullDto>() {
        }.getType();

        Gson gson = new Gson();
        ProductFullDto productFullDto = gson.fromJson(response.getBody(), type);
        return productFullDto;
    }

    public Boolean productLike(Long id) {
        String url = "http://localhost:8080/api/v1/product/like/create";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", SessionToken.getSession().getUserId());
        jsonObject.put("productId", id);
        HttpEntity<Object> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
        });
        return response.getStatusCode().is2xxSuccessful();
    }

    public Boolean productCommentCreate(Long id, ProductCommentCreateDto productCommentCreateDto) {
        String url = "http://localhost:8080/api/v1/product/comment/create";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", SessionToken.getSession().getUserId());
        jsonObject.put("productId", id);
        jsonObject.put("message", productCommentCreateDto.getReview());
        jsonObject.put("rating", productCommentCreateDto.getRating());
        HttpEntity<Object> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
        });
        return response.getStatusCode().is2xxSuccessful();
    }

    public List<ProductDto> getProductByFilterAndPagination(FilterDto filterDto, Integer page) {
        String url = "http://localhost:8080/api/v1/product";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        org.springframework.boot.configurationprocessor.json.JSONObject jsonObject = new org.springframework.boot.configurationprocessor.json.JSONObject();
        try {
            jsonObject.put("size", 20);
            jsonObject.put("page", page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpEntity<Object> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });

        Type type = new TypeToken<List<ProductDto>>() {
        }.getType();

        Gson gson = new Gson();
        List<ProductDto> list = gson.fromJson(response.getBody(), type);
        return list;
    }
}
