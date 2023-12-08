package com.example.draw.service;

import com.example.draw.domain.dto.SendDrawResponse;
import com.example.draw.domain.entity.Animal;
import com.example.draw.domain.entity.Building;
import com.example.draw.domain.entity.Grade;
import com.example.draw.domain.kafka.KafkaProducerService;
import com.example.draw.domain.response.DrawAnimalsResponse;
import com.example.draw.domain.response.DrawBuildingResponse;
import com.example.draw.repository.DrawBuildingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DrawBuildingsService {

    private final DrawBuildingsRepository drawBuildingsRepository;
    private final KafkaProducerService kafkaProducerService;

    public List<DrawBuildingResponse> pick(int number, UUID userUUID) {
        // 1 or 10 뽑는 횟수
        int type = 1;

        List<SendDrawResponse> sendList = new ArrayList<>();
        List<DrawBuildingResponse> resultList = new ArrayList<>();
        // 리스트 선언 보여줄때 1개 혹은 10개를 보여줘야 해서

        for (int i = 0; i < number; i++) {
            int x = (int)(Math.random() * 100);
            // ** Math.random()은 double이라서 바로 Integer형변환이 안된다. 기본형인 int로 바꿔준 후에 Integer로 바꿀수가 있다.
            Grade grade;
            Building pickBuilding = null; // 초기화 설정
            if (x == 0) { // 1% 레전드
                grade = Grade.LEGEND;
                pickBuilding = drawBuildingsRepository.pick(grade);
            } else if (x <= 3) { // 3% 유니크
                grade = Grade.UNIQUE;
                pickBuilding = drawBuildingsRepository.pick(grade);
            } else if (x <= 11) { // 8% 슈퍼레어
                grade = Grade.SUPERRARE;
                pickBuilding = drawBuildingsRepository.pick(grade);
            } else if (x <= 32) { // 21% 레어
                grade = Grade.RARE;
                pickBuilding = drawBuildingsRepository.pick(grade);
            } else if (x <= 99) { // 67% 노말
                grade = Grade.NORMAL;
                pickBuilding = drawBuildingsRepository.pick(grade);
            }

            if (pickBuilding != null) {
                sendList.add(new SendDrawResponse(
                        // List에 넣고 user에 kafka전송
                        pickBuilding.getName(),
                        userUUID
                ));

                resultList.add(new DrawBuildingResponse(
                        // List에 넣고 마지막에 보여준다.
                        pickBuilding.getName(),
                        type,
                        pickBuilding.getImagePath()));
            }
        }
        kafkaProducerService.sendBuildingResult(sendList);
        // userBuilding에 저장시키게 보내준다.
        return resultList;
    }




}
