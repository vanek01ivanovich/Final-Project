package com.example.finalProjectEpam.service.serviceInterfaces;

import com.example.finalProjectEpam.entity.PriceListCities;
import com.example.finalProjectEpam.entity.Ticket;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;

public interface TicketService {
    void addUserTicket(PriceListCities ticketCity, UsersDetails user,Ticket ticket);
}
