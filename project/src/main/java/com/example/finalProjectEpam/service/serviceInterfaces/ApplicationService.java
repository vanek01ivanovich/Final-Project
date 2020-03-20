package com.example.finalProjectEpam.service.serviceInterfaces;

import com.example.finalProjectEpam.entity.Application;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationService {
    //Application addStationFromTo(String stationFrom,String stationTo);
    //void deleteStation(String station);
    //void addDate(String date);
    void addStationFromTo(Integer id,String date,String stationFrom,String stationTo);


}
