package com.example.draw.domain.response;

import com.example.draw.domain.entity.Species;

import java.util.UUID;

public record DrawAnimalsResponse(
        String name,
        Species type,
        String imagePath

) {
}
