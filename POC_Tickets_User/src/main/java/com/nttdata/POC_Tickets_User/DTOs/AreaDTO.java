package com.nttdata.POC_Tickets_User.DTOs;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO {

    private Long id;

    @NotNull
    private String name;

    private List<ProjectDTO> projectList;
}
