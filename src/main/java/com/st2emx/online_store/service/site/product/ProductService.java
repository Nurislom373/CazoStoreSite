package com.st2emx.online_store.service.site.product;

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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

import static com.st2emx.online_store.utils.BaseUtils.parseTo;
import static com.st2emx.online_store.utils.BaseUtils.sendUrl;

@Service
public class ProductService implements BaseService {

    public ProductFullDto getProductFull(Long id) {
        String url = "http://localhost:8080/api/v1/product/full/" + id;
        ResponseEntity<String> response = sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        return parseTo(response.getBody(), ProductFullDto.class);
    }

    public Boolean productLike(Long id) {
        String url = "http://localhost:8080/api/v1/product/like/create";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", SessionToken.getSession().getUserId());
        jsonObject.put("productId", id);
        return sendUrl(url, HttpMethod.POST, MediaType.APPLICATION_JSON, jsonObject).getStatusCode().is2xxSuccessful();
    }

    public Boolean productLikeDelete(Long id) {
        String url = "http://localhost:8080/api/v1/product/like/" + id + "/" + SessionToken.getSession().getUserId();
        return sendUrl(url, HttpMethod.DELETE, MediaType.APPLICATION_JSON).getStatusCode().is2xxSuccessful();
    }

    public Boolean productCommentCreate(Long id, ProductCommentCreateDto productCommentCreateDto) {
        String url = "http://localhost:8080/api/v1/product/comment/create";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", SessionToken.getSession().getUserId());
        jsonObject.put("productId", id);
        jsonObject.put("message", productCommentCreateDto.getReview());
        jsonObject.put("rating", productCommentCreateDto.getRating());
        return sendUrl(url, HttpMethod.POST, MediaType.APPLICATION_JSON, jsonObject).getStatusCode().is2xxSuccessful();
    }

    public List<ProductDto> getProductByFilterAndPagination(FilterDto filterDto, Integer page) {
        String url = "http://localhost:8080/api/v1/product/filter";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size", 20);
        jsonObject.put("page", page);
        jsonObject.put("sortById", filterDto.getSortById());
        jsonObject.put("priceId", filterDto.getPriceId());
        jsonObject.put("colorId", filterDto.getColorId());
        ResponseEntity<String> response = sendUrl(url, HttpMethod.POST, MediaType.APPLICATION_JSON, jsonObject);
        return new Gson().fromJson(response.getBody(), new TypeToken<List<ProductDto>>() {
        }.getType());
    }

    public List<ProductDto> getProductBySearchAndPagination(String word, Integer page) {
        String url = "http://localhost:8080/api/v1/product/search/" + word;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size", 20);
        jsonObject.put("page", page);
        ResponseEntity<String> response = sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON, jsonObject);
        return new Gson().fromJson(response.getBody(), new TypeToken<List<ProductDto>>() {
        }.getType());
    }
}