package com.example.finalProjectEpam.repository;

import com.example.finalProjectEpam.entity.Application;
import com.example.finalProjectEpam.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
   Optional<Application> getTicketByStationFromAndStationTo(String stationFrom, String stationTo);


}
