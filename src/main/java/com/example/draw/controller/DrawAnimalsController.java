package com.example.draw.controller;

import com.example.draw.domain.response.DrawAnimalsResponse;
import com.example.draw.service.DrawAnimalsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/draw/animal")
public class DrawAnimalsController {

    private final DrawAnimalsService drawService;

    @PostMapping("/{number}") // path로 1번뽑을건지 10번 뽑을건지 입력받는다.
    public List<DrawAnimalsResponse> pick(@PathVariable("number") Integer number){
        return drawService.pick(number);
    }

}
