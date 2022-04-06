package com.st2emx.online_store.utils;

import com.google.gson.Gson;
import com.st2emx.online_store.dto.file.FileDto;
import lombok.SneakyThrows;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BaseUtils {

    public static String getCookie(Cookie[] cookies, String name) {
        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equalsIgnoreCase(name)).findFirst().map(Cookie::getValue).orElse(null);
    }

    public static Boolean checkCookies(Cookie[] cookies) {
        if (Objects.isNull(cookies)) return false;
        return Arrays.stream(cookies).anyMatch(cookie -> cookie.getName().equals("userId") && Objects.nonNull(cookie.getValue()));
    }

    @SneakyThrows
    public static FileDto sendFileUpload(String url, MultipartFile file) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        org.apache.http.HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody("file", file.getInputStream(), ContentType.DEFAULT_BINARY, file.getOriginalFilename()).build();
        HttpUriRequest request = RequestBuilder.post(url).setEntity(data).build();
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                org.apache.http.HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        String responseBody = httpClient.execute(request, responseHandler);
        return parseTo(responseBody, FileDto.class);
    }

    public static Cookie createCookie(String cookieName, String value, Integer aliveTime) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(aliveTime * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    public static Cookie createCookie(String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(2 * 24 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }

    public static <T> T parseTo(String body, Class<T> tClass) {
        return new Gson().fromJson(body, tClass);
    }

    public static ResponseEntity<String> sendUrl(String url, HttpMethod httpMethod, MediaType mediaType) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return template.exchange(url, httpMethod, entity, new ParameterizedTypeReference<>() {
        });
    }

    public static ResponseEntity<String> sendUrl(String url, HttpMethod httpMethod, MediaType mediaType, String bearer) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer ".concat(bearer));
        headers.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return template.exchange(url, httpMethod, entity, new ParameterizedTypeReference<>() {
        });
    }

    public static ResponseEntity<String> sendUrl(String url, HttpMethod httpMethod, MediaType mediaType, JSONObject jsonObject) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        return template.exchange(url, httpMethod, entity, new ParameterizedTypeReference<>() {
        });
    }

    public static ResponseEntity<String> sendUrl(String url, HttpMethod httpMethod, MediaType mediaType, JSONObject jsonObject, String bearer) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer ".concat(bearer));
        headers.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        return template.exchange(url, httpMethod, entity, new ParameterizedTypeReference<>() {
        });
    }
}
