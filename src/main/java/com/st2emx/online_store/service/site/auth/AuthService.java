package com.st2emx.online_store.service.site.auth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.dto.auth.LoginDto;
import com.st2emx.online_store.dto.auth.RegisterDto;
import com.st2emx.online_store.dto.file.FileDto;
import com.st2emx.online_store.dto.token.TokenDto;
import com.st2emx.online_store.service.BaseService;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;

import static com.st2emx.online_store.utils.BaseUtils.*;

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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", registerDto.getFirstName());
        jsonObject.put("lastName", registerDto.getLastName());
        jsonObject.put("username", registerDto.getUsername());
        jsonObject.put("email", registerDto.getEmail());
        jsonObject.put("password", registerDto.getPassword());
        jsonObject.put("image_path", image_path);
        return sendUrl(url, HttpMethod.POST, MediaType.APPLICATION_JSON, jsonObject).getStatusCode().is2xxSuccessful();
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
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.set("file", multipartFile.getInputStream());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        return getFileDto(url, requestEntity, restTemplate);
    }

    private FileDto getFileDto(String url, HttpEntity<MultiValueMap<String, Object>> requestEntity, RestTemplate restTemplate) {
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
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
        return getFileDto(url, entity, template);
    }

    public TokenDto loginProcessing(LoginDto loginDto) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", loginDto.getUsername());
        jsonObject.put("password", loginDto.getPassword());
        ResponseEntity<String> response = sendUrl("http://localhost:8080/api/v1/auth/token", HttpMethod.POST, MediaType.APPLICATION_JSON, jsonObject);
        return parseTo(response.getBody(), TokenDto.class);
    }

}
