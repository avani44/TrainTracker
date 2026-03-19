package com.train.trace.train.Service; 
import org.springframework.stereotype.Service;
import com.train.trace.train.Entity.Train;
import com.train.trace.train.Repo.TrainRepository;

import java.util.List;
@Service
public class TrainService {
    private TrainRepository trainRepository;
    public TrainService(TrainRepository trainRepository){
        this.trainRepository = trainRepository;
    }

    public List<Train> getAllTrains(){
        return trainRepository.findAll();
    }

    public Train addTrain(Train train){
        return trainRepository.save(train);
    }
}
