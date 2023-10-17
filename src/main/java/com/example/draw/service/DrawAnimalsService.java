package com.example.draw.service;

import com.example.draw.domain.entity.Animal;
import com.example.draw.domain.response.DrawAnimalsResponse;
import com.example.draw.repository.DrawAnimalsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DrawAnimalsService {

    private final DrawAnimalsRepository drawRepository;

    public List<DrawAnimalsResponse> pick(int number) { // 1 or 10 뽑는 횟수
        List<DrawAnimalsResponse> resultList = new ArrayList<>(); // 리스트 선언 보여줄때 1개 혹은 10개를 보여줘야 해서

        for (int i = 0; i < number; i++) {
            Integer x = (int)(Math.random() * 100); // ** Math.random()은 double이라서 바로 Integer형변환이 안된다. 기본형인 int로 바꿔준 후에 Integer로 바꿀수가 있다.

            Animal pickAnimal = null; // 초기화 설정
            if (x == 0) { // 1% 레전드
                pickAnimal = drawRepository.legendPick();
            } else if (x <= 3) { // 3% 유니크
                pickAnimal = drawRepository.uniquePick();
            } else if (x <= 11) { // 8% 슈퍼레어
                pickAnimal = drawRepository.sRPick();
            } else if (x <= 32) { // 21% 레어
                pickAnimal = drawRepository.rarePick();
            } else if (x <= 99) { // 67% 노말
                pickAnimal = drawRepository.normalPick();
            }

            if (pickAnimal != null) {
                resultList.add(new DrawAnimalsResponse( // List에 넣고 마지막에 보여준다.
                        pickAnimal.getName(),
                        pickAnimal.getGrade(),
                        pickAnimal.getAttackPower(),
                        pickAnimal.getDefencePower(),
                        pickAnimal.getLife(),
                        pickAnimal.getType()
                ));
            }
        }

        return resultList;
    }




}
