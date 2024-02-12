package com.nttdata.POC_Tickets_User.Service;

import com.nttdata.POC_Tickets_User.DTOs.TicketDTO;
import com.nttdata.POC_Tickets_User.DTOs.UserDTO;
import com.nttdata.POC_Tickets_User.Repository.ProjectRepository;
import com.nttdata.POC_Tickets_User.Repository.TicketRepository;
import com.nttdata.POC_Tickets_User.Repository.UserRepository;
import com.nttdata.POC_Tickets_User.entities.Project;
import com.nttdata.POC_Tickets_User.entities.Ticket;
import com.nttdata.POC_Tickets_User.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    public enum AssigneeType {
        USER,
        PROJECT
    }

    public TicketDTO getTicketById(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));

        return modelMapper.map(ticket, TicketDTO.class);
    }

    public TicketDTO createTicket(TicketDTO ticketDTO){

        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        Ticket createdTicket = ticketRepository.save(ticket);

        return modelMapper.map(createdTicket, TicketDTO.class);
    }

    public List<TicketDTO> getAllTickets() {

        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }

    public boolean deleteTicket(Long id){

        if(ticketRepository.existsById(id)){

            ticketRepository.deleteById(id);
            return !ticketRepository.existsById(id);

        } else {

            return false;
        }
    }

    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
        modelMapper.map(ticketDTO, ticket);

        Ticket updatedTicket = ticketRepository.save(ticket);

        return modelMapper.map(updatedTicket, TicketDTO.class);
    }

    public TicketDTO updateProgress(Long ticketId, double completedWork) {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setCompletedWork(completedWork);
        ticket.setProgress(calculateProgress(ticket));
        Ticket updatedTicket = ticketRepository.save(ticket);

        return modelMapper.map(updatedTicket, TicketDTO.class);
    }

    private double calculateProgress(Ticket ticket){

        double estimate = ticket.getEstimate();
        double completedWork = ticket.getCompletedWork();

        if( estimate == 0){

            return 0;
        }

        return (completedWork /estimate) * 100;
    }

    public List<Ticket> getTopTicketsByEstimationVsProgress(Long projectId) {

        List<Ticket> projectTickets = ticketRepository.findByProjectId(projectId);

        projectTickets.sort(Comparator.comparingDouble(this::calculateEstimationVsProgressDifference).reversed());

        return projectTickets.subList(0, Math.min(10, projectTickets.size()));
    }

    private double calculateEstimationVsProgressDifference(Ticket ticket) {

        return ticket.getEstimate() - ticket.getCompletedWork();
    }
    public List<TicketDTO> searchByPriority(String priority, Long projectId) {

        List<Ticket> tickets = ticketRepository.findByPriority(priority, projectId);
        List<TicketDTO> ticketDTOList = new ArrayList<>();

        for(Ticket ticket: tickets){
            TicketDTO ticketDTO = modelMapper.map(ticket, TicketDTO.class);
            ticketDTOList.add(ticketDTO);
        }

        return ticketDTOList;
    }

    public List<TicketDTO> searchByStatus(String status, Long projectId) {

       List<Ticket> tickets = ticketRepository.findByStatus(status, projectId);

        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }

    public void updateTicketPriority(Long ticketId, Ticket.Priority priority) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket Not Found with id: " + ticketId));

        ticket.setPriority(priority);
        ticketRepository.save(ticket);
    }

    public void updateTicketStatus(Long ticketId, Ticket.Status status) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));

        ticket.setStatus(status);
        ticketRepository.save(ticket);
    }

    public void assignTicket(Long ticketId, AssigneeType assigneeType, Long assigneeId){

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));

        switch (assigneeType) {
            case USER:
                Optional<User> assigneeUser = Optional.ofNullable(userRepository.findById(assigneeId).orElseThrow(() -> new RuntimeException("User not found")));
                if (assigneeUser.isPresent()) {
                    User userToAssign = assigneeUser.get();
                    ticket.setAssignedTo(userToAssign);
                    ticketRepository.save(ticket);
                }
                break;
            case PROJECT:
                Optional<Project> assigneeProject = Optional.ofNullable(projectRepository.findById(assigneeId).orElseThrow(() -> new RuntimeException("Project not found ")));
                if (assigneeProject.isPresent()) {
                    Project projectToAssign = assigneeProject.get();
                    ticket.setProject(projectToAssign);
                    ticketRepository.save(ticket);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported assignee type: " + assigneeType);
        }

    }
}
