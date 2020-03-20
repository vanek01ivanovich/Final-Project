package com.example.finalProjectEpam.service.implementation;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.repository.TicketRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.TicketService;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Locale;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private Ticket ticket;
    @PersistenceContext
    private EntityManager entityManager;




    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void addUserTicket(PriceListCities ticketCity, UsersDetails user,Ticket ticket) {

        Locale locale = LocaleContextHolder.getLocale();
        //String userFirstName = user.getFirstName();
        String INSERT_SQL;
        SqlParameterSource parameters;
        if (locale == Locale.ENGLISH) {
            INSERT_SQL = "INSERT INTO ticket " +
                    "(station_from,station_to,train_name,user_ticket_name,user_ticket_last_name) " +
                    "VALUES (:from,:to,:train,:name,:lastName)";

            parameters = new MapSqlParameterSource()
                    /*.addValue("id", ticketCity.getId())*/
                    .addValue("from", ticketCity.getStationFrom())
                    .addValue("to", ticketCity.getStationTo())
                    .addValue("train", ticketCity.getTrainName())
                    .addValue("name", user.getFirstName())
                    .addValue("lastName", user.getLastName());

            namedParameterJdbcTemplate.update(INSERT_SQL, parameters);
        }else {
            INSERT_SQL = "INSERT INTO ticket " +
                    "(station_from_ukr,station_to_ukr,train_name,user_ticket_name,user_ticket_last_name) " +
                    "VALUES (:from,:to,:train,:name,:lastName)";

            parameters = new MapSqlParameterSource()
                  /*  .addValue("id", ticketCity.getId())*/
                    .addValue("from", ticketCity.getStationFromUkr())
                    .addValue("to", ticketCity.getStationToUkr())
                    .addValue("train", ticketCity.getTrainName())
                    .addValue("name", user.getFirstName())
                    .addValue("lastName", user.getLastName());

            namedParameterJdbcTemplate.update(INSERT_SQL, parameters);
        }

        /*if (locale == Locale.ENGLISH) {
            entityManager.createNativeQuery("insert into ticket (station_from,station_to,train_name,user_ticket_name,user_ticket_last_name)" +
                    "values ('d','d','s','s','v')");
                    *//*.setParameter(1,"fdsvsd")
                    .setParameter(2,ticketCity.getStationTo())
                    .setParameter(3,ticketCity.getTrainName())
                    .setParameter(4,"vdssdc")
                    .setParameter(5,user.getLastName());*//*

            ticketRepository.save(ticket);
        }*/

    }

   /* @Override
    public Ticket addUserTicket(Ticket ticket) {
        entityManager.createNativeQuery("insert into ticket " +
                "(station_from, station_to, user_ticket_last_name, user_ticket_name) " +
                "values (?,?,?,?)")
                .setParameter(1,)
    }*/
}
