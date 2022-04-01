package com.st2emx.online_store.service.admin.size;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.dto.size.SizeCreateDto;
import com.st2emx.online_store.dto.size.SizeDto;
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
public class SizeService implements BaseService {

    public List<SizeDto> getAllSizes() {
        String url = "http://localhost:8080/api/v1/size/list";
        ResponseEntity<String> response = BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        Type type = new TypeToken<List<SizeDto>>() {
        }.getType();
        System.out.println(response.getBody());
        return new Gson().fromJson(response.getBody(), type);
    }

    public void create(SizeCreateDto dto) {
        String url = "http://localhost:8080/api/v1/size/create";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", dto.getCode());
        jsonObject.put("number", dto.getNumber());
        BaseUtils.sendUrl(url, HttpMethod.POST, MediaType.APPLICATION_JSON, jsonObject);
    }

    public void delete(Long id) {
        String url = "http://localhost:8080/api/v1/size/delete/" + id;
        BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
    }

    public SizeDto get(Long id) {
        String url = "http://localhost:8080/api/v1/size/" + id;
        ResponseEntity<String> response = BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        return BaseUtils.parseTo(response.getBody(), SizeDto.class);
    }

    public void update(Long id, SizeCreateDto dto) {
        String url = "http://localhost:8080/api/v1/size/update";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("number", dto.getNumber());
        jsonObject.put("code", dto.getCode());
        BaseUtils.sendUrl(url, HttpMethod.PUT, MediaType.APPLICATION_JSON);
    }
}
