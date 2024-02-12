package com.nttdata.POC_Tickets_User.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailsDTO {

        private Long id;

        private String name;

        private String description;

        private List<UserDTO> members;

        private List<TicketDTO> tickets;


}
