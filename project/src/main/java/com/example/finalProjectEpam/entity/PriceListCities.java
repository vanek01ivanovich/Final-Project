package com.example.finalProjectEpam.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.sql.Time;
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

    @Column(name = "TIME_DEPARTURE")
    private String timeDeparture;

    @Column(name = "TIME_ARRIVAL")
    private String timeArrival;

    /*@Column(name = "DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.sql.Date date;*/

    @Column(name = "DATE")
    private String date;

    @Column(name = "DATE_ARRIVAL")
    private String dateArrival;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateU;

    @Column(name = "TRAIN_NAME")
    private String trainName;

    /*@ManyToMany(mappedBy = "priceListCities")
    private Collection<Train> trainCollection;*/


}
