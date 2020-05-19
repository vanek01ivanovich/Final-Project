package com.example.finalProjectEpam.service.implementation;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.repository.PriceListCitiesRepository;
import com.example.finalProjectEpam.repository.TicketRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.TicketService;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    private PriceListCitiesRepository priceListCitiesRepository;



    private Ticket ticket;





    @Autowired
    public TicketServiceImpl(PriceListCitiesRepository priceListCitiesRepository,
                             TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
        this.priceListCitiesRepository = priceListCitiesRepository;
    }

    @Override
    public void addUserTicket(PriceListCities ticketCity, UsersDetails user,Ticket ticket) {

        ticketRepository.addUserTicket(user.getUsername(),ticketCity.getPrice(), ticketCity.getStationFrom(),
                ticketCity.getStationTo() , user.getLastName(), user.getFirstName(), ticketCity.getTrainName(),
                ticketCity.getStationFromUkr(), ticketCity.getStationToUkr(),user.getLastNameUkr(),
                user.getFirstNameUkr(),ticketCity.getDate(),ticketCity.getDateArrival(),ticketCity.getTimeDeparture(),
                ticketCity.getTimeArrival());
    }

    public  void findCity(PriceListCities city) {
        List<PriceListCities> cities;

        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH){
            cities =  priceListCitiesRepository.findCityByStationFromAndStationToAndDate(city.getStationFrom(), city.getStationTo(),city.getDate());
            city.setStationFromUkr(cities.get(0).getStationFromUkr());
            city.setStationToUkr(cities.get(0).getStationToUkr());
        }else{
            cities =priceListCitiesRepository.findCityByStationFromUkrAndStationToUkrAndDate(city.getStationFromUkr(),city.getStationToUkr(),city.getDate());
            city.setStationFrom(cities.get(0).getStationFrom());
            city.setStationTo(cities.get(0).getStationTo());
        }
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
