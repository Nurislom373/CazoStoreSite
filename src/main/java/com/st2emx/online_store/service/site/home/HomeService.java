package com.st2emx.online_store.service.site.home;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.dto.auth.UserDto;
import com.st2emx.online_store.dto.category.CategoryDto;
import com.st2emx.online_store.dto.file.FileDto;
import com.st2emx.online_store.dto.product.ProductDto;
import com.st2emx.online_store.dto.product.ProductOnlyDto;
import com.st2emx.online_store.entity.color.Color;
import com.st2emx.online_store.service.BaseService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.management.MemoryUsage;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.List;

import static com.st2emx.online_store.utils.BaseUtils.*;

@Service
public class HomeService implements BaseService {

    public List<ProductDto> homeProcessing() {
        String url = "http://localhost:8080/api/v1/product";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size", 2);
        jsonObject.put("page", 0);
        ResponseEntity<String> response = sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON, jsonObject);
        return new Gson().fromJson(response.getBody(), new TypeToken<List<ProductDto>>() {
        }.getType());
    }

    public List<ProductDto> getProducts() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size", 2);
        jsonObject.put("page", 0);
        ResponseEntity<String> response = sendUrl("http://localhost:8080/api/v1/product", HttpMethod.GET, MediaType.APPLICATION_JSON, jsonObject);
        return new Gson().fromJson(response.getBody(), new TypeToken<List<ProductDto>>() {
        }.getType());
    }

    public List<CategoryDto> getAllCategories() {
        String url = "http://localhost:8080/api/v1/category/list";
        ResponseEntity<String> response = sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        return new Gson().fromJson(response.getBody(), new TypeToken<List<CategoryDto>>() {
        }.getType());
    }

    public List<Color> getAllColor() {
        String url = "http://localhost:8080/api/v1/color/list";
        ResponseEntity<String> response = sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        return new Gson().fromJson(response.getBody(), new TypeToken<List<Color>>() {
        }.getType());
    }

    public List<ProductOnlyDto> getAllProductLike() {
        String url = "http://localhost:8080/api/v1/product/like/user/" + SessionToken.getSession().getUserId();
        ResponseEntity<String> response = sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        return new Gson().fromJson(response.getBody(), new TypeToken<List<ProductOnlyDto>>() {
        }.getType());
    }

    public UserDto getUserById(Long userId) {
        String url = "http://localhost:8080/api/v1/auth/" + userId;
        ResponseEntity<String> response = sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        return parseTo(response.getBody(), UserDto.class);
    }

    public Integer getLike() {
        List<ProductOnlyDto> list = getAllProductLike();
        return list.size();
    }
}
