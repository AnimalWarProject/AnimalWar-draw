package com.example.draw.domain.response;

public record DrawBuildingResponse(
        String name,
        String grade,
        Integer attackPower,
        Integer defencePower,
        Integer life
) {
}
