package com.st2emx.online_store.service.admin.product;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.dto.product.ProductDto;
import com.st2emx.online_store.service.BaseService;
import com.st2emx.online_store.utils.BaseUtils;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class AdminProductService implements BaseService {

    public List<ProductDto> getAll() {
        String url = "http://localhost:8080/api/v1/product";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size", 20);
        jsonObject.put("page", 0);
        ResponseEntity<String> response = BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON, jsonObject);
        Type type = new TypeToken<List<ProductDto>>() {
        }.getType();
        return new Gson().fromJson(response.getBody(), type);
    }
}
