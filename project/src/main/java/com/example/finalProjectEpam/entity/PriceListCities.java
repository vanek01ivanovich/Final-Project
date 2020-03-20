package com.example.finalProjectEpam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "destination")
public class PriceListCities {

    @Id
    @GeneratedValue
    @Column(name = "DESTINATION_ID")
    private Integer id;

    @Column(name = "STATION_FROM")
    private String stationFrom;

    @Column(name = "STATION_FROM_UKR")
    private String stationFromUkr;

    @Column(name = "STATION_TO")
    private String stationTo;

    @Column(name = "STATION_TO_UKR")
    private String stationToUkr;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "TIME")
    private String time;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "TRAIN_NAME")
    private String trainName;

    /*@ManyToMany(mappedBy = "priceListCities")
    private Collection<Train> trainCollection;*/


}
