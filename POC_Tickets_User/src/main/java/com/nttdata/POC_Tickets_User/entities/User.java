package com.nttdata.POC_Tickets_User.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "USER_PROFILE")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String mail;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "createdBy")
    private List<Ticket> createdTickets;

    @ManyToMany(mappedBy = "members")
    private List<Project> projectList;
}
