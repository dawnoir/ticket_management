package com.nttdata.POC_Tickets_User.DTOs;

import com.nttdata.POC_Tickets_User.entities.Project;
import com.nttdata.POC_Tickets_User.entities.User;
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
public class ProjectDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private AreaDTO area;

}
