package com.example.finalProjectEpam.service.implementation;

import com.example.finalProjectEpam.repository.ApplicationRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ApplicationRepository applicationRepository;


    @Override
    public void addStationFromTo(Integer id, String date,String stationFrom, String stationTo) {
        entityManager.createNativeQuery("insert into application (application_id,date,station_from, station_to) values (?,?,?,?)")
                .setParameter(1,id)
                .setParameter(2,date)
                .setParameter(3,stationFrom)
                .setParameter(4,stationTo)
                .executeUpdate();
    }


}
