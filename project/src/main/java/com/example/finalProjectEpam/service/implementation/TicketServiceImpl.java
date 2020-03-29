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
import java.util.List;
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

        String INSERT_SQL;
        SqlParameterSource parameters;

            INSERT_SQL = "INSERT INTO ticket" +
                    " (user_name" +
                    ",price," +
                    " station_from," +
                    " station_to," +
                    " user_ticket_last_name," +
                    " user_ticket_name," +
                    " train_name," +
                    " station_from_ukr," +
                    " station_to_ukr," +
                    "user_ticket_name_ukr," +
                    " user_ticket_last_name_ukr," +
                    " date," +
                    "date_arrival, " +
                    "time_arrival, " +
                    "time_departure) " +

                    "VALUES(" +
                    ":userName," +
                    ":price," +
                    ":stationFrom," +
                    ":stationTo," +
                    ":lastName," +
                    ":firstName," +
                    ":trainName," +
                    ":stationFromUkr," +
                    ":stationToUkr," +
                    ":lastNameUkr," +
                    ":firstNameUkr," +
                    ":dateDepart," +
                    ":dateArr," +
                    ":timeDepart," +
                    ":timeArr)";


        parameters = new MapSqlParameterSource()
                .addValue("userName", user.getUsername())
                .addValue("price", ticketCity.getPrice())
                .addValue("stationFrom", ticketCity.getStationFrom())
                .addValue("stationTo", ticketCity.getStationTo())
                .addValue("lastName", user.getLastName())
                .addValue("firstName", user.getFirstName())
                .addValue("trainName", ticketCity.getTrainName())
                .addValue("stationFromUkr", ticketCity.getStationFromUkr())
                .addValue("stationToUkr", ticketCity.getStationToUkr())
                .addValue("lastNameUkr",user.getLastNameUkr())
                .addValue("firstNameUkr",user.getFirstNameUkr())
                .addValue("dateDepart",ticketCity.getDate())
                .addValue("dateArr",ticketCity.getDateArrival())
                .addValue("timeDepart",ticketCity.getTimeDeparture())
                .addValue("timeArr",ticketCity.getTimeArrival());

            namedParameterJdbcTemplate.update(INSERT_SQL, parameters);


    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllTicketsByUserName(String userName) {
        return ticketRepository.findAllByUserName(userName);
    }


}
