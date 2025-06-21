// File: src/main/java/com/hist/traffic/controller/RootController.java
package com.hist.traffic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String home() {
        return "🚦 Traffic Analytics Backend is running. Visit /traffic-stream for live data.";
    }
}
