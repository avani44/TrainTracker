package com.train.trace.train.Entity;  

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;



@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String trainName;
    private String trainNumber;
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TrainSchedule> scheduleList;

    public Train() {

    }

    public Train(long id, String trainName, String trainNumber, List<TrainSchedule> scheduleList) {
        this.id = id;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.scheduleList = scheduleList;
    }

    


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrainName() {
        return this.trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return this.trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public List<TrainSchedule> getScheduleList() {
        return this.scheduleList;
    }

    public void setScheduleList(List<TrainSchedule> scheduleList) {
        this.scheduleList = scheduleList;
    }


}