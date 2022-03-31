package com.st2emx.online_store.service.admin.color;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.dto.color.ColorCreateDto;
import com.st2emx.online_store.dto.color.ColorDto;
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
public class ColorService implements BaseService {

    public List<ColorDto> getAllColors() {
        String url = "http://localhost:8080/api/v1/color/list";
        ResponseEntity<String> response = BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        Type type = new TypeToken<List<ColorDto>>() {
        }.getType();
        return new Gson().fromJson(response.getBody(), type);
    }

    public void create(ColorCreateDto dto) {
        String url = "http://localhost:8080/api/v1/color/create";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", dto.getName());
        BaseUtils.sendUrl(url, HttpMethod.POST, MediaType.APPLICATION_JSON, jsonObject);
    }

    public void delete(Long id) {
        String url = "http://localhost:8080/api/v1/color/delete/" + id;
        BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
    }

    public ColorDto get(Long id) {
        String url = "http://localhost:8080/api/v1/color/" + id;
        ResponseEntity<String> response = BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        return BaseUtils.parseTo(response.getBody(), ColorDto.class);
    }

    public void update(Long id, ColorCreateDto dto) {
        String url = "http://localhost:8080/api/v1/color/update";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", dto.getName());
        BaseUtils.sendUrl(url, HttpMethod.PUT, MediaType.APPLICATION_JSON, jsonObject);
    }
}
