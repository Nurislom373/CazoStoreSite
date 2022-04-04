package com.st2emx.online_store.service.site.authCart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.dto.cart.CartCreateDto;
import com.st2emx.online_store.dto.cart.CartDto;
import com.st2emx.online_store.dto.cart.CartUpdateDto;
import com.st2emx.online_store.service.BaseService;
import org.json.JSONObject;
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
public class AuthCartService implements BaseService {

    public Boolean create(CartCreateDto dto, Long id) {
        String url = "http://localhost:8080/api/v1/auth/cart/create";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", "Bearer ".concat(SessionToken.session.getAccessToken()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", SessionToken.getSession().getUserId());
        jsonObject.put("productId", id);
        jsonObject.put("count", dto.getCount());
        jsonObject.put("accept", dto.getAccept());
        HttpEntity<Object> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
        });
        return response.getStatusCode().is2xxSuccessful();
    }

    public List<CartDto> getCards() {
        String url = "http://localhost:8080/api/v1/auth/cart/list/" + SessionToken.getSession().getUserId();
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });
        Type type = new TypeToken<List<CartDto>>() {
        }.getType();
        Gson gson = new Gson();
        List<CartDto> cartDtos = gson.fromJson(response.getBody(), type);
        System.out.println(cartDtos);
        return cartDtos;
    }

    public CartDto updateCart(CartUpdateDto dto){
        String url = "http://localhost:8080/api/v1/auth/cart/update/"+dto.getId();
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", dto.getCount());
        jsonObject.put("id", dto.getId());
        HttpEntity<Object> entity = new HttpEntity<>(jsonObject.toString(),headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.PUT, entity, new ParameterizedTypeReference<>() {
        });

        Type type = new TypeToken<CartDto>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(response.getBody(), type);
    }

    public String sumCartPrice() {
        String url = "http://localhost:8080/api/v1/auth/cart/sumCartPrice/" + SessionToken.getSession().getUserId();
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });
        Type type = new TypeToken<String>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(response.getBody(), type);
    }
}
