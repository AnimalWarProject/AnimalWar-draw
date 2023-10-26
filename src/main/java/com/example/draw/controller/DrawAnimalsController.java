package com.example.draw.controller;

import com.example.draw.domain.request.DrawRequest;
import com.example.draw.domain.response.DrawAnimalsResponse;
import com.example.draw.service.DrawAnimalsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/draw/animal")
public class DrawAnimalsController {

    private final DrawAnimalsService drawService;

    @PostMapping //  1번뽑을건지 10번 뽑을건지 입력받는다.
    public List<DrawAnimalsResponse> pick(@RequestBody DrawRequest request){
        return drawService.pick(request.cnt(), request.userUUID());
    }

}
