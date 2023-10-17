package com.example.draw.service;

import com.example.draw.domain.entity.Building;
import com.example.draw.domain.response.DrawBuildingResponse;
import com.example.draw.repository.DrawBuildingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DrawBuildingsService {

    private final DrawBuildingsRepository drawBuildingsRepository;

    public List<DrawBuildingResponse> pick(int number) { // 1 or 10 뽑는 횟수
        List<DrawBuildingResponse> resultList = new ArrayList<>(); // 리스트 선언 보여줄때 1개 혹은 10개를 보여줘야 해서

        for (int i = 0; i < number; i++) {
            Integer x = (int)(Math.random() * 100); // ** Math.random()은 double이라서 바로 Integer형변환이 안된다. 기본형인 int로 바꿔준 후에 Integer로 바꿀수가 있다.

            Building pickBuilding = null; // 초기화 설정
            if (x == 0) { // 1% 레전드
                pickBuilding = drawBuildingsRepository.legendPick();
            } else if (x <= 3) { // 3% 유니크
                pickBuilding = drawBuildingsRepository.uniquePick();
            } else if (x <= 11) { // 8% 슈퍼레어
                pickBuilding = drawBuildingsRepository.sRPick();
            } else if (x <= 32) { // 21% 레어
                pickBuilding = drawBuildingsRepository.rarePick();
            } else if (x <= 99) { // 67% 노말
                pickBuilding = drawBuildingsRepository.normalPick();
            }

            if (pickBuilding != null) {
                resultList.add(new DrawBuildingResponse( // List에 넣고 마지막에 보여준다.
                        pickBuilding.getName(),
                        pickBuilding.getGrade(),
                        pickBuilding.getAttackPower(),
                        pickBuilding.getDefencePower(),
                        pickBuilding.getLife()
                ));
            }
        }

        return resultList;
    }




}
