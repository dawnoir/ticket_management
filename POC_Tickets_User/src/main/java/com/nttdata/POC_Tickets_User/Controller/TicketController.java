package com.nttdata.POC_Tickets_User.Controller;

import com.nttdata.POC_Tickets_User.DTOs.TicketDTO;
import com.nttdata.POC_Tickets_User.Service.TicketService;
import com.nttdata.POC_Tickets_User.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {

        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);

        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {

        List<TicketDTO> ticketDTOList = ticketService.getAllTickets();

        return ResponseEntity.ok(ticketDTOList);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long ticketId) {

        TicketDTO ticketDTO = ticketService.getTicketById(ticketId);

        return ResponseEntity.ok(ticketDTO);
    }


    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long ticketId) {

        boolean deleted = ticketService.deleteTicket(ticketId);

        if (deleted) {

            return ResponseEntity.ok("Ticket deleted successfully");
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long ticketId, @RequestBody TicketDTO ticketDTO) {

        TicketDTO updatedTicket = ticketService.updateTicket(ticketId, ticketDTO);

        return ResponseEntity.ok(updatedTicket);
    }

    @PutMapping("/{ticketId}/updateProgress")
    public ResponseEntity<TicketDTO> updateProgress(@PathVariable Long ticketId, @RequestBody Map<String, Double> requestBody) {

        double completedWork = requestBody.get("completedWork");
        TicketDTO updatedTicket = ticketService.updateProgress(ticketId, completedWork);

        return ResponseEntity.ok(updatedTicket);
    }

    @GetMapping("/{projectId}/status")
    public ResponseEntity<List<TicketDTO>> findTicketsByStatus(@RequestParam String status, @RequestParam Long projectId){

        List<TicketDTO> ticketDTOList = ticketService.searchByStatus(status, projectId);

        if(ticketDTOList.isEmpty()){

            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.ok(ticketDTOList);
        }
    }

    @GetMapping("/{projectId}/priority")
    public ResponseEntity<List<TicketDTO>> findTicketsByProgress(@RequestParam String priority, @RequestParam Long projectId){

        List<TicketDTO> ticketDTOList = ticketService.searchByPriority(priority,projectId);

        if(ticketDTOList.isEmpty()){

            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.ok(ticketDTOList);
        }
    }

    @PostMapping("/{ticketId}/updatePriority")
    public ResponseEntity<String> updateTicketPriority(@PathVariable Long ticketId, @RequestBody Map<String, String> requestBody){

        String priorityValue = requestBody.get("priority");

        if( priorityValue == null || priorityValue.isEmpty()){

            return  ResponseEntity.badRequest().body("Priority not found");
        }

        Ticket.Priority priority = Ticket.Priority.valueOf(priorityValue);
        ticketService.updateTicketPriority(ticketId, priority);

        return ResponseEntity.ok("Ticket priority updated");

    }

    @PostMapping("/{ticketId}/updateStatus")
    public ResponseEntity<String> updateTicketStatus(@PathVariable Long ticketId, @RequestBody Map<String, String> requestBody) {

        String statusValue = requestBody.get("status");
        if (statusValue == null || statusValue.isEmpty()) {
            return ResponseEntity.badRequest().body("Status not Found");
        }

        Ticket.Status status = Ticket.Status.valueOf(statusValue);
        ticketService.updateTicketStatus(ticketId, status);

        return ResponseEntity.ok("Ticket Status updated");
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignTicket(@RequestParam Long ticketId, @RequestParam TicketService.AssigneeType assigneeType, @RequestParam Long assigneeId) {

        if (ticketId <= 0 || assigneeType == null || assigneeId <= 0) {
            return ResponseEntity.badRequest().body("Invalid input parameters");
        }


        ticketService.assignTicket(ticketId, assigneeType, assigneeId);

        return ResponseEntity.ok("Ticket assigned successfully");
    }
}
