package com.hist.traffic.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hist.traffic.entity.TrafficData;
import com.hist.traffic.repository.TrafficDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TrafficDataConsumer {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private TrafficDataRepository trafficDataRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "vehicle_data", groupId = "group_id")
    public void consumeTrafficData(String message) {
        // Send to front-end
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                emitter.completeWithError(e);
                emitters.remove(emitter);
            }
        });

        // Parse JSON and save to DB
        try {
            // Assuming message is like: {"intersectionId": "...", "vehicleCount": ..., "timestamp": ...}
            var jsonNode = objectMapper.readTree(message);

            TrafficData data = new TrafficData();
            data.setLocation(jsonNode.get("intersectionId").asText());
            data.setVehicleCount(jsonNode.get("vehicleCount").asInt());
            data.setTimestamp(jsonNode.get("timestamp").asLong());

            trafficDataRepository.save(data);
            System.out.println("Saved to DB: " + data.getLocation());
        } catch (Exception e) {
            System.err.println("Failed to save to DB: " + e.getMessage());
        }
    }

    public SseEmitter addEmitter() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        return emitter;
    }
}
