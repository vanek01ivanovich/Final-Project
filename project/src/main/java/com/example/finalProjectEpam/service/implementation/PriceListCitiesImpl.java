package com.example.finalProjectEpam.service.implementation;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.repository.PriceListCitiesRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.PriceListCitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PriceListCitiesImpl implements PriceListCitiesService {
    @Autowired
    private PriceListCitiesRepository priceListCitiesRepository;

    @Autowired
    public PriceListCitiesImpl(PriceListCitiesRepository priceListCitiesRepository){
        this.priceListCitiesRepository = priceListCitiesRepository;
    }

    public List<PriceListCities> findCityByStationFromAndTo(String stationFrom, String stationTo){
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH){
            return priceListCitiesRepository.findCityByStationFromAndStationTo(stationFrom,stationTo);
        }else{
            return priceListCitiesRepository.findCityByStationFromUkrAndStationToUkr(stationFrom,stationTo);
        }
    }


    public List<PriceListCities> findTicketByStationFromAndStationToAndTrainName(String stationFrom,String stationTo,String trainName){
        return priceListCitiesRepository.findTicketByStationFromAndStationToAndTrainName(stationFrom,stationTo,trainName);
    }

    public PriceListCities getId(Long id){
        return priceListCitiesRepository.findById(id).get();
    }

}
