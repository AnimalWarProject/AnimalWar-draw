package com.example.draw.domain.response;

public record DrawAnimalsResponse(
        String name,
        String grade,
        Integer attackPower,
        Integer defencePower,
        Integer life,
        String type
) {
}
