package com.example.finalProjectEpam.repository;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findAllByUserName(String userName);


    @Modifying
    @Query(value = "INSERT INTO ticket(user_name,price,station_from,station_to," +
                                      "user_ticket_last_name,user_ticket_name," +
                                      "train_name,station_from_ukr,station_to_ukr," +
                                      "user_ticket_name_ukr,user_ticket_last_name_ukr," +
                                      "date,date_arrival,time_arrival,time_departure) " +
                    "VALUES(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15)",nativeQuery = true)
    void addUserTicket(String userName,Integer price,String stationFrom,String stationTo,
                       String userTicketLastName,String userTicketName,String trainName,
                       String stationFromUkr,String stationToUkr,String userTicketNameUkr,
                       String userTicketLastNameUkr,String date,String dateArrival,
                       String timeArrival,String timeDeparture);
}
