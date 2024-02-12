package com.nttdata.POC_Tickets_User.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "TICKET")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double estimate;

    @Column(nullable = false)
    private Double progress;

    @Column(nullable = false)
    private Double completedWork;

    @ManyToOne
    @JoinColumn(name = "createdBy_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assignedTo_id")
    private User assignedTo;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    public enum Priority{
        HIGH,
        MEDIUM,
        LOW
    }

    public enum Status{
        OPEN,
        IN_PROGRESS,
        CLOSED
    }
}
