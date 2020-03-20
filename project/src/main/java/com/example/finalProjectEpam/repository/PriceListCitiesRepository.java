package com.example.finalProjectEpam.repository;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Train;
import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PriceListCitiesRepository  extends JpaRepository<PriceListCities,Long> {
    /*@Query("SELECT statioт_from,station_to,date FROM  destination d " +
            "where d.station_from  = :stationFrom " +
            "and d.station_to = :stationTo " +
            "and d.date = :date")*/

    List<PriceListCities> findTicketByStationFromAndStationToAndTrainName(String stationFrom,String stationTo,String trainName);

    List<PriceListCities> findCityByStationFromAndStationTo(String stationFrom,
                                                            String stationTo);

    List<PriceListCities> findCityByStationFromAndStationToAndDate(String stationFrom,
                                                                   String stationTo,String date);

    List<PriceListCities> findCityByDate( Date date);

    List<PriceListCities> findCityByStationFromUkrAndStationToUkr(String stationFromUkr,
                                                            String stationToUkr);

    List<PriceListCities> findCityByStationFromUkrAndStationToUkrAndDate(String stationFrom, String stationTo,String date);
    //Optional<PriceListCities> findCityByStationFrom(@Param("stationFrom") String stationFrom);
}
