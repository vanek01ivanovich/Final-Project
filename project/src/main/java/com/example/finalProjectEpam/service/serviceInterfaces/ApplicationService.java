package com.example.finalProjectEpam.service.serviceInterfaces;

import com.example.finalProjectEpam.entity.Application;
import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ApplicationService {
    //Application addStationFromTo(String stationFrom,String stationTo);
    //void deleteStation(String station);
    //void addDate(String date);
    void addStationFromTo(Integer id,String date,String stationFrom,String stationTo);

    void addApplication(PriceListCities priceListCities, UsersDetails user);

    List<Application> getAllApplications();
}
