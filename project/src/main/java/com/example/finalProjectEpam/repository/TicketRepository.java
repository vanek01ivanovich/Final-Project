package com.example.finalProjectEpam.repository;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    //Optional<Ticket> findTicketByStationFromAndStationToAndTrainName(String stationFrom,String stationTo,String trainName);
}
