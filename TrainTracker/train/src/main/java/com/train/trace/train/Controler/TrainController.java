package com.train.trace.train.Controler;  
import java.util.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.train.trace.train.Service.TrainService;  
import com.train.trace.train.Entity.Train;

@RestController
@RequestMapping("/train")
public class TrainController {
    private TrainService trainService;
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }
    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    @PostMapping
    public Train addTrain(@RequestBody Train train){
        return this.trainService.addTrain(train);
    }

    @GetMapping("/test")
    public void test() {
        
    }
}