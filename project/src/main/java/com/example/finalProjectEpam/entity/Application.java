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
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue
    @Column(name = "APPLICATION_ID")
    private Integer id;

    @Column(name = "STATION_FROM")
    private String stationFrom;

    @Column(name = "STATION_FROM_UKR")
    private String stationFromUkr;

    @Column(name = "STATION_TO")
    private String stationTo;

    @Column(name = "STATION_TO_UKR")
    private String stationToUkr;

    @Column(name = "DATE")
    private String date;

}
