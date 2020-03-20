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
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue
    @Column(name = "TRAIN_ID")
    private Integer id;

    @Column(name = "TRAIN_NAME")
    private String trainName;

    @Column(name = "TRAIN_NAME_UKR")
    private String trainNameUkr;

}

