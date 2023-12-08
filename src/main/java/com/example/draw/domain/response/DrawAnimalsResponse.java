package com.example.draw.domain.response;

import com.example.draw.domain.entity.Species;

public record DrawAnimalsResponse(
        String name,
        Species type,
        String imagePath

) {
}
