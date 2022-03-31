package com.st2emx.online_store.service.admin.category;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.dto.category.CategoryCreateDto;
import com.st2emx.online_store.dto.category.CategoryDto;
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
public class CategoryService implements BaseService {

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

    public void create(CategoryCreateDto dto) {
        String url = "http://localhost:8080/api/v1/category/create";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", dto.getName());
        HttpEntity<Object> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
        });
    }

    public void delete(Long id) {
        String url = "http://localhost:8080/api/v1/category/" + id;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.DELETE, entity, new ParameterizedTypeReference<>() {
        });
    }

    public CategoryDto get(Long id) {
        String url = "http://localhost:8080/api/v1/category/" + id;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });
        Type type = new TypeToken<CategoryDto>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(response.getBody(), type);
    }

    public void update(Long id, CategoryCreateDto dto) {
        String url = "http://localhost:8080/api/v1/category/update";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
//        headers.add("Authorization", "Bearer ".concat(SessionToken.getSession().getAccessToken()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", dto.getName());
        HttpEntity<Object> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.PUT, entity, new ParameterizedTypeReference<>() {
        });
    }
}
