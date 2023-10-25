package com.example.draw.domain.kafka;

import com.example.draw.domain.response.DrawAnimalsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "resultDraw";
    private final KafkaTemplate<String, List<DrawAnimalsResponse>> kafkaTemplate;

    public void sendResult(List<DrawAnimalsResponse> result) {
        System.out.println("result producer = " + result);
        kafkaTemplate.send(TOPIC, result);
    }

}