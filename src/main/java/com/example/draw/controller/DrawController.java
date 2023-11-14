package com.example.draw.controller;

import com.example.draw.domain.request.DrawRequest;
import com.example.draw.domain.response.DrawAnimalsResponse;
import com.example.draw.domain.response.DrawBuildingResponse;
import com.example.draw.service.DrawAnimalsService;
import com.example.draw.service.DrawBuildingsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/draw/")
public class DrawController {

    private final DrawAnimalsService animalsService;
    private final DrawBuildingsService buildingsService;

    @PostMapping("/animal") //  1번뽑을건지 10번 뽑을건지 입력받는다.
    public List<DrawAnimalsResponse> animalPick(@RequestBody DrawRequest request){
        return animalsService.pick(request.cnt(), request.userUUID());
    }

    @PostMapping("/building")
    public List<DrawBuildingResponse> buildingPick(@RequestBody DrawRequest request){
        return buildingsService.pick(request.cnt(), request.userUUID());
    }

}
