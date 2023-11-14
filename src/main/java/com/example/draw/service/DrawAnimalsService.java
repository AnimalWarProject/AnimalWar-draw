package com.example.draw.service;

import com.example.draw.domain.dto.SendDrawResponse;
import com.example.draw.domain.entity.Animal;
import com.example.draw.domain.entity.Grade;
import com.example.draw.domain.response.DrawAnimalsResponse;
import com.example.draw.domain.kafka.KafkaProducerService;
import com.example.draw.repository.DrawAnimalsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DrawAnimalsService {

    private final DrawAnimalsRepository drawRepository;
    private final KafkaProducerService kafkaProducerService;

    public List<DrawAnimalsResponse> pick(int number, UUID userUUID) { // 1 or 10 뽑는 횟수

        List<SendDrawResponse> sendList = new ArrayList<>();
        List<DrawAnimalsResponse> resultList = new ArrayList<>();// 리스트 선언 보여줄때 1개 혹은 10개를 보여줘야 해서

        for (int i = 0; i < number; i++) {
            int x = (int)(Math.random() * 100); // ** Math.random()은 double이라서 바로 Integer형변환이 안된다. 기본형인 int로 바꿔준 후에 Integer로 바꿀수가 있다.
            Grade grade;
            Animal pickAnimal = null; // 초기화 설정
            if (x == 0) { // 1% 레전드
                grade = Grade.LEGEND;
                pickAnimal = drawRepository.pick(grade);
            } else if (x <= 3) { // 3% 유니크
                grade = Grade.UNIQUE;
                pickAnimal = drawRepository.pick(grade);
            } else if (x <= 11) { // 8% 슈퍼레어
                grade = Grade.SUPERRARE;
                pickAnimal = drawRepository.pick(grade);
            } else if (x <= 32) { // 21% 레어
                grade = Grade.RARE;
                pickAnimal = drawRepository.pick(grade);
            } else if (x <= 99) { // 67% 노말
                grade = Grade.NORMAL;
                pickAnimal = drawRepository.pick(grade);
            }

            if (pickAnimal != null) {
                sendList.add(new SendDrawResponse( // List에 넣고 user에 kafka전송
                        pickAnimal.getName(),
                        userUUID
                ));
                resultList.add(new DrawAnimalsResponse( // List에 넣고 마지막에 보여준다.
                        pickAnimal.getName()));
            }
        }
        kafkaProducerService.sendAnimalResult(sendList);  // userAnimal에 저장시키게 보내준다.
        return resultList;
    }

}
