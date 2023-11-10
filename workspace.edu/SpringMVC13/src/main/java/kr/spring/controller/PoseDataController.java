package kr.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@CrossOrigin(origins = "http://localhost:8081") // 클라이언트의 URL
@RestController
public class PoseDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(PoseDataController.class);

    @PostMapping("/api/savePoseData")
    public ResponseEntity<?> savePoseData(@RequestBody String poseData) {
    	
    	logger.info("Received request to save pose data");
    	
        try {
            String filePath = "C:\\Users\\gjaischool1\\My project (6)\\Assets\\poseData.json";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(poseData);
            }
            return ResponseEntity.ok().body("Data saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving data");
        }
    }
}
