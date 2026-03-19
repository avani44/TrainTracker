package com.train.trace.train.Controler; 

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.train.trace.train.Entity.TrainSchedule;
import com.train.trace.train.Service.TrainSearchService;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class TrainSearchController {
    private TrainSearchService trainSearchService;
    private TrainSearchController(TrainSearchService trainSearchService) {
        this.trainSearchService=trainSearchService;
    }

    @GetMapping("/by-code")
    public List<TrainSchedule> findTrainByStationCode (@RequestParam String sourceCode, @RequestParam String destinationCode) {
        return trainSearchService.findTrainByStationCode(sourceCode.toUpperCase(), destinationCode.toUpperCase());
    }

    @GetMapping("/by-name")
    public List<TrainSchedule> findTrainByStationName (@RequestParam String sourceName, @RequestParam String destinationName) {
        return trainSearchService.findTrainByStationName(sourceName.toUpperCase(), destinationName.toUpperCase());
    }
}
