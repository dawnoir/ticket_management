package com.nttdata.POC_Tickets_User.Controller;

import com.nttdata.POC_Tickets_User.DTOs.ProjectDTO;
import com.nttdata.POC_Tickets_User.DTOs.ProjectDetailsDTO;
import com.nttdata.POC_Tickets_User.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/{projectId}/addMembers/{userId}")
    public ResponseEntity<String> addMembersToProject(@PathVariable Long projectId, @PathVariable Long userId) {

        try {

            projectService.addUserToProject(projectId, userId);
            return ResponseEntity.ok("Members added to project successfully.");

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailsDTO> getProjectDetails(@PathVariable Long projectId) {

        try {

            ProjectDetailsDTO projectDetailsDTO = projectService.getProjectDetails(projectId);
            return ResponseEntity.ok(projectDetailsDTO);

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<String> assignProjectToArea(@PathVariable Long projectId, @PathVariable Long areaId ){

        try {

            projectService.assignProjectToArea(projectId, areaId);
            return ResponseEntity.ok("Project assigned successfully to Area");

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
