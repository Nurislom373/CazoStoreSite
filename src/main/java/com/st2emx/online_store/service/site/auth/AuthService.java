package com.st2emx.online_store.service.site.auth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.dto.auth.*;
import com.st2emx.online_store.dto.file.FileDto;
import com.st2emx.online_store.dto.token.TokenDto;
import com.st2emx.online_store.service.BaseService;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.lang.reflect.Type;

import static com.st2emx.online_store.utils.BaseUtils.*;

@Service
public class AuthService implements BaseService {

    @SneakyThrows
    public Boolean register(RegisterDto registerDto) {
        FileDto fileDto = sendFileUpload("http://localhost:8080/api/v1/file/user/upload", registerDto.getFile());
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

    private FileDto getFileDto(String url, HttpEntity<MultiValueMap<String, Object>> requestEntity, RestTemplate restTemplate) {
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        Gson gson = new Gson();
        Type type = new TypeToken<FileDto>() {
        }.getType();
        FileDto fileDto = gson.fromJson(response.getBody(), type);
        return fileDto;
    }

    public TokenDto loginProcessing(LoginDto loginDto) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", loginDto.getUsername());
        jsonObject.put("password", loginDto.getPassword());
        ResponseEntity<String> response = sendUrl("http://localhost:8080/api/v1/auth/token", HttpMethod.POST, MediaType.APPLICATION_JSON, jsonObject);
        return parseTo(response.getBody(), TokenDto.class);
    }

    public UserDto getUserDetails(Long userId) {
        String url = "http://localhost:8080/api/v1/auth/"+userId;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });
        Gson gson = new Gson();
        Type type = new TypeToken<UserDto>() {
        }.getType();
        UserDto userDto = gson.fromJson(response.getBody(), type);
        return userDto;
    }

    @SneakyThrows
    public boolean updateProfile(Integer id, UserUpdateDto userUpdateDto) throws JSONException {
        String url = "http://localhost:8080/api/v1/auth/update";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        org.springframework.boot.configurationprocessor.json.JSONObject jsonObject = new org.springframework.boot.configurationprocessor.json.JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("username", userUpdateDto.getUsername());
        jsonObject.put("firstName", userUpdateDto.getFirstName());
        jsonObject.put("lastName", userUpdateDto.getLastName());
        jsonObject.put("email", userUpdateDto.getEmail());
        jsonObject.put("phone", userUpdateDto.getPhone());
        if (userUpdateDto.getImage_path() != null) {
            FileDto fileDto = sendFileUpload("http://localhost:8080/api/v1/file/user/upload", userUpdateDto.getImage_path());
            jsonObject.put("image_path", fileDto.getPath());
        }
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.PUT, entity, new ParameterizedTypeReference<>() {
        });
        return response.getStatusCode().is2xxSuccessful();
    }

    public boolean updateUserSettings(Integer id, UserSettingsDto userSettingsDto) throws JSONException {
        String url = "http://localhost:8080/api/v1/auth/updateSettings";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        org.springframework.boot.configurationprocessor.json.JSONObject jsonObject = new org.springframework.boot.configurationprocessor.json.JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("language_code", userSettingsDto.getLanguage_code());
        jsonObject.put("price_type", userSettingsDto.getPrice_type());
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.PUT, entity, new ParameterizedTypeReference<>() {
        });
        return response.getStatusCode().is2xxSuccessful();
    }

    public boolean changePassword(Integer id, ChangePasswordDto changePasswordDto) throws JSONException {
        String url = "http://localhost:8080/api/v1/auth/changePassword";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        org.springframework.boot.configurationprocessor.json.JSONObject jsonObject = new org.springframework.boot.configurationprocessor.json.JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("password", changePasswordDto.getPassword());
        jsonObject.put("newPassword", changePasswordDto.getNewPassword());
        jsonObject.put("renewPassword", changePasswordDto.getRenewPassword());
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.PUT, entity, new ParameterizedTypeReference<>() {
        });
        return response.getStatusCode().is2xxSuccessful();
    }

}
