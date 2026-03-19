package com.train.trace.train.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.trace.train.Entity.Station;
import com.train.trace.train.Entity.Train;
import com.train.trace.train.Entity.TrainSchedule;
import com.train.trace.train.Repo.StationRepository;
import com.train.trace.train.Repo.TrainRepository;
import com.train.trace.train.Repo.TrainScheduleRepository;

import java.util.List;

@RestController
@RequestMapping("/test")
public class Test {
    @Autowired
    StationRepository stationRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainScheduleRepository trainScheduleRepository;

    @GetMapping
    public void test() {
        Station delhi = new Station();
        delhi.setStationName("NEW DELHI");
        delhi.setStationCode("NDLS");

        Station mumbai = new Station();
        mumbai.setStationName("MUMBAI CENTRAL");
        mumbai.setStationCode("CST");

        Station kolkata = new Station();
        kolkata.setStationName("KOLKATA");
        kolkata.setStationCode("KOAA");

        Station chennai = new Station();
        chennai.setStationName("CHENNAI CENTRAL");
        chennai.setStationCode("MAS");

        stationRepository.saveAll(List.of(delhi, mumbai, kolkata, chennai));


        Train rajdhani = new Train();
        rajdhani.setTrainName("RAJDHANI EXPRESS");
        rajdhani.setTrainNumber("12306");

        Train duronto = new Train();
        duronto.setTrainName("DURONTO EXPRESS");
        duronto.setTrainNumber("12260");

        Train shatabdi = new Train();
        shatabdi.setTrainName("SHATABDI EXPRESS");
        shatabdi.setTrainNumber("12043");

        trainRepository.saveAll(List.of(rajdhani, duronto, shatabdi));


        TrainSchedule sc1 = new TrainSchedule();
        sc1.setTrain(rajdhani);
        sc1.setSource(delhi);
        sc1.setDestination(mumbai);
        sc1.setDepartureTime("06:00");
        sc1.setArrivalTime("14:00");

        TrainSchedule sc2 = new TrainSchedule();
        sc2.setTrain(duronto);
        sc2.setSource(mumbai);
        sc2.setDestination(kolkata);
        sc2.setDepartureTime("08:00");
        sc2.setArrivalTime("21:00");

        TrainSchedule sc3 = new TrainSchedule();
        sc3.setTrain(shatabdi);
        sc3.setSource(kolkata);
        sc3.setDestination(chennai);
        sc3.setDepartureTime("11:30");
        sc3.setArrivalTime("19:00");

        trainScheduleRepository.saveAll(List.of(sc1, sc2, sc3));

        System.out.println("Data inserted in database");
    }
}
