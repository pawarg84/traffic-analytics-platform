package com.hist.traffic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.hist.traffic.kafka.TrafficProducer;

@SpringBootApplication
public class TrafficBackendApplication implements CommandLineRunner {

    @Autowired
    private TrafficProducer trafficProducer;

    public static void main(String[] args) {
        SpringApplication.run(TrafficBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        trafficProducer.streamTrafficData(); // Streams to Kafka on app start
    }
}
