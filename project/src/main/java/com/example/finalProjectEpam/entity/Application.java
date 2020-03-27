package com.example.finalProjectEpam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "applicationtable")
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

    @Pattern(regexp = "[A-Z][a-z]{2,20}")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Pattern(regexp = "[А-ЯІЩЄҐЇ][а-ящєґ'ії]+")
    @Column(name = "FIRST_NAME_UKR")
    private String firstNameUkr;

    @Pattern(regexp = "[A-Z][a-z]{2,20}")
    @Column(name = "LAST_NAME")
    private String lastName;

    @Pattern(regexp = "[А-ЯІЩЄҐЇ][а-ящєґ'ії]+")
    @Column(name = "LAST_NAME_UKR")
    private String lastNameUkr;

}
