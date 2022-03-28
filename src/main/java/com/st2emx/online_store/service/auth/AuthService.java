package com.st2emx.online_store.service.auth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.dto.auth.LoginDto;
import com.st2emx.online_store.dto.auth.RegisterDto;
import com.st2emx.online_store.dto.category.CategoryDto;
import com.st2emx.online_store.dto.file.FileDto;
import com.st2emx.online_store.dto.token.TokenDto;
import com.st2emx.online_store.service.BaseService;
import lombok.SneakyThrows;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Objects;

@Service
public class AuthService implements BaseService {

    @SneakyThrows
    public Boolean register(RegisterDto registerDto) {
        FileDto fileDto = imageProcessing(registerDto.getFile());
        return registerProcessing(registerDto, fileDto.getPath());
    }

    @SneakyThrows
    public Boolean registerProcessing(RegisterDto registerDto, String image_path) {
        String url = "http://localhost:8080/api/v1/auth/register";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", registerDto.getFirstName());
        jsonObject.put("lastName", registerDto.getLastName());
        jsonObject.put("username", registerDto.getUsername());
        jsonObject.put("email", registerDto.getEmail());
        jsonObject.put("password", registerDto.getPassword());
        jsonObject.put("image_path", image_path);
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
        });
        return response.getStatusCode().is2xxSuccessful();
    }

    @SneakyThrows
    public TokenDto login(LoginDto loginDto) {
        TokenDto tokenDto = loginProcessing(loginDto);
        SessionToken.setSession(tokenDto);
        return tokenDto;
    }

    @SneakyThrows
    public FileDto imageProcessing(MultipartFile multipartFile) {
        String url = "http://localhost:8080/api/v1/file/upload";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        headers.add("Content-Type", "multipart/form-data; boundary=something");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(multipartFile.getBytes()));
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
        });
        Gson gson = new Gson();
        Type type = new TypeToken<FileDto>() {
        }.getType();
        FileDto fileDto = gson.fromJson(response.getBody(), type);
        return fileDto;
    }

    public FileDto imageUpload(MultipartFile file) {
        String url = "http://localhost:8080/api/v1/file/upload";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> stringResponseEntity = template.postForEntity(url, entity, String.class);
        Gson gson = new Gson();
        Type type = new TypeToken<FileDto>() {
        }.getType();
        FileDto fileDto = gson.fromJson(stringResponseEntity.getBody(), type);
        return fileDto;
    }

    public static TokenDto loginProcessing(LoginDto loginDto) throws JSONException {
        String url = "http://localhost:8080/api/v1/auth/token";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", loginDto.getUsername());
        jsonObject.put("password", loginDto.getPassword());
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
        });
        Gson gson = new Gson();
        Type type = new TypeToken<TokenDto>() {
        }.getType();
        TokenDto tokenDto = gson.fromJson(response.getBody(), type);
        return tokenDto;
    }
}
