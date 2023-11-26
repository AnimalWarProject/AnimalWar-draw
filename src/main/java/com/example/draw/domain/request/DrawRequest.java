package com.example.draw.domain.request;

import java.util.UUID;

public record DrawRequest (Integer count, UUID userUUID){
}
