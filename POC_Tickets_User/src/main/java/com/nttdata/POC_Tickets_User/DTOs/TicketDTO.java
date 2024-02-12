package com.nttdata.POC_Tickets_User.DTOs;

import com.nttdata.POC_Tickets_User.entities.Ticket;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    private String title;

    @NotNull
    private String status;

    @NotNull
    private String priority;

    @NotNull
    private String type;

    @NotNull
    private Double estimate;

    @NotNull
    private Double progress;

    @NotNull
    private Double completedWork;

    private Long createdByUserId;

    private Long assignedToUserId;

    private Date creationDate;

    private Long projectId;

    public TicketDTO(Long id, String title, String description, Ticket.Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status.toString();
    }
}
