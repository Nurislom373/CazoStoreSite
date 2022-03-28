package com.st2emx.online_store.service.home;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.dto.auth.UserDto;
import com.st2emx.online_store.dto.category.CategoryDto;
import com.st2emx.online_store.dto.product.ProductDto;
import com.st2emx.online_store.dto.product.ProductOnlyDto;
import com.st2emx.online_store.entity.category.Category;
import com.st2emx.online_store.entity.color.Color;
import com.st2emx.online_store.service.BaseService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
public class HomeService implements BaseService {

    public List<ProductDto> homeProcessing() {
        String url = "http://localhost:8080/api/v1/product";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("size", 2);
            jsonObject.put("page", 0);
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

    public List<CategoryDto> getAllCategories() {
        String url = "http://localhost:8080/api/v1/category/list";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });

        Type type = new TypeToken<List<CategoryDto>>() {
        }.getType();

        Gson gson = new Gson();
        List<CategoryDto> list = gson.fromJson(response.getBody(), type);
        return list;
    }

    public List<Color> getAllColor() {
        String url = "http://localhost:8080/api/v1/color/list";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });

        Type type = new TypeToken<List<Color>>() {
        }.getType();

        Gson gson = new Gson();
        List<Color> list = gson.fromJson(response.getBody(), type);
        return list;
    }

    public Integer getLike() {
        List<ProductOnlyDto> list = getAllProductLike();
        return list.size();
    }

    public List<ProductOnlyDto> getAllProductLike() {
        String url = "http://localhost:8080/api/v1/product/like/user/" + SessionToken.getSession().getUserId();
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });

        Type type = new TypeToken<List<ProductOnlyDto>>() {
        }.getType();

        Gson gson = new Gson();
        List<ProductOnlyDto> list = gson.fromJson(response.getBody(), type);
        System.out.println("list = " + list);
        return list;
    }

    public UserDto getUserById(Long userId) {
        String url = "http://localhost:8080/api/v1/auth/" + userId;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });
        Type type = new TypeToken<UserDto>() {
        }.getType();
        Gson gson = new Gson();
        UserDto dto = gson.fromJson(response.getBody(), type);
        return dto;
    }
}
