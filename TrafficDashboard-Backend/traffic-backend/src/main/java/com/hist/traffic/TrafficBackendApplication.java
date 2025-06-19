package com.hist.traffic;

import com.hist.traffic.kafka.TrafficProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrafficBackendApplication implements CommandLineRunner {

    @Autowired
    private TrafficProducer trafficProducer;

    @Value("${spring.kafka.producer.bootstrap-servers:}")
    private String kafkaServers;

    public static void main(String[] args) {
        SpringApplication.run(TrafficBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (kafkaServers != null && !kafkaServers.isEmpty() && !kafkaServers.equals("localhost:9092")) {
            trafficProducer.streamTrafficData();
        } else {
            System.out.println("🚫 Kafka not configured or localhost fallback detected. Skipping data streaming.");
        }
    }
}
