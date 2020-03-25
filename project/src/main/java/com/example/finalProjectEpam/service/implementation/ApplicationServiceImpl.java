package com.example.finalProjectEpam.service.implementation;

import com.example.finalProjectEpam.entity.Application;
import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.repository.ApplicationRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.ApplicationService;

import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void addStationFromTo(Integer id, String date,String stationFrom, String stationTo) {
        entityManager.createNativeQuery("insert into application (application_id,date,station_from, station_to) values (?,?,?,?)")
                .setParameter(1,id)
                .setParameter(2,date)
                .setParameter(3,stationFrom)
                .setParameter(4,stationTo)
                .executeUpdate();
    }

    @Override
    public void addApplication(PriceListCities application, UsersDetails user){
        String INSERT_SQL;
        SqlParameterSource parameters;

            INSERT_SQL = "INSERT INTO application " +
                    "(station_from,station_to,station_from_ukr,station_to_ukr,first_name,last_name,date,first_name_ukr,last_name_ukr) " +
                    "VALUES (:fromStation,:toStation,:fromStationUkr,:toStationUkr,:firstName,:lastName,:dateApp,:firstNameUkr,:lastNameUkr)";

            parameters = new MapSqlParameterSource()
                    /*.addValue("id", ticketCity.getId())*/
                    .addValue("fromStation", application.getStationFrom())
                    .addValue("toStation", application.getStationTo())
                    .addValue("fromStationUkr", application.getStationFromUkr())
                    .addValue("toStationUkr", application.getStationToUkr())
                    .addValue("firstName", user.getFirstName())
                    .addValue("lastName", user.getLastName())
                    .addValue("firstNameUkr", user.getFirstNameUkr())
                    .addValue("lastNameUkr",user.getLastNameUkr())
                    .addValue("dateApp",application.getDate());






        namedParameterJdbcTemplate.update(INSERT_SQL, parameters);
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }


}
