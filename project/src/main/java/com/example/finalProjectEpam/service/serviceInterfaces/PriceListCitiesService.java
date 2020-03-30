package com.example.finalProjectEpam.service.serviceInterfaces;

import com.example.finalProjectEpam.entity.PriceListCities;

import java.util.List;

public interface PriceListCitiesService {

    List<PriceListCities> findCitiesForUserRequest(String stationFrom, String stationTo, String date,PriceListCities city);

}
