package com.st2emx.online_store.service.admin.feedback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.st2emx.online_store.dto.feedback.FeedbackDto;
import com.st2emx.online_store.service.BaseService;
import com.st2emx.online_store.utils.BaseUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class FeedbackService implements BaseService {

    public List<FeedbackDto> getAllFeedbacks() {
        String url = "http://localhost:8080/api/v1/feedback/list";
        ResponseEntity<String> response = BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        Type type = new TypeToken<List<FeedbackDto>>() {
        }.getType();
        return new Gson().fromJson(response.getBody(), type);
    }


    public void delete(Long id) {
        String url = "http://localhost:8080/api/v1/feedback/delete/" + id;
        BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
    }

    public FeedbackDto get(Long id) {
        String url = "http://localhost:8080/api/v1/feedback/" + id;
        ResponseEntity<String> response = BaseUtils.sendUrl(url, HttpMethod.GET, MediaType.APPLICATION_JSON);
        return BaseUtils.parseTo(response.getBody(), FeedbackDto.class);
    }
}
