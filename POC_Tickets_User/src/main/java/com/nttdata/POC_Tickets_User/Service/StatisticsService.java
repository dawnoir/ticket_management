package com.nttdata.POC_Tickets_User.Service;

import com.nttdata.POC_Tickets_User.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    private TicketRepository ticketRepository;

}
