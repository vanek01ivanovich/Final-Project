package com.example.finalProjectEpam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue
    @Column(name = "TICKET_ID")
    private Integer id;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "USER_TICKET_NAME")
    private String userFirstNameForTicket;

    @Column(name = "USER_TICKET_NAME_UKR")
    private String userFirstNameUkrForTicket;

    @Column(name = "USER_TICKET_LAST_NAME")
    private String userLastNameForTicket;

    @Column(name = "USER_TICKET_LAST_NAME_UKR")
    private String userLastNameUkrForTicket;

    @Column(name = "STATION_FROM")
    private String stationFrom;

    @Column(name = "STATION_FROM_UKR")
    private String stationFromUkr;

    @Column(name = "STATION_TO")
    private String stationTo;

    @Column(name = "STATION_TO_UKR")
    private String stationToUkr;

    @Column(name = "TRAIN_NAME")
    private String trainName;

    @Column(name = "TRAIN_NAME_UKR")
    private String trainNameUkr;

    @Column(name = "TIME_DEPARTURE")
    private String timeDeparture;

    @Column(name = "TIME_ARRIVAL")
    private String timeArrival;
}
