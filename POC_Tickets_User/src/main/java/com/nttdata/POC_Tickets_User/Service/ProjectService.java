package com.nttdata.POC_Tickets_User.Service;

import com.nttdata.POC_Tickets_User.DTOs.ProjectDTO;
import com.nttdata.POC_Tickets_User.DTOs.ProjectDetailsDTO;
import com.nttdata.POC_Tickets_User.DTOs.TicketDTO;
import com.nttdata.POC_Tickets_User.DTOs.UserDTO;
import com.nttdata.POC_Tickets_User.Repository.AreaRepository;
import com.nttdata.POC_Tickets_User.Repository.ProjectRepository;
import com.nttdata.POC_Tickets_User.Repository.TicketRepository;
import com.nttdata.POC_Tickets_User.Repository.UserRepository;
import com.nttdata.POC_Tickets_User.entities.Area;
import com.nttdata.POC_Tickets_User.entities.Project;
import com.nttdata.POC_Tickets_User.entities.Ticket;
import com.nttdata.POC_Tickets_User.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Project createProject(Project project){

        return projectRepository.save(project);
    }

    public boolean deleteProject(Long id){

        if(projectRepository.existsById(id)){
            projectRepository.deleteById(id);
            return !projectRepository.existsById(id);
        } else {
            return false;
        }

    }

    public List<ProjectDTO> getAllProjects(){

        List<Project> projectList = projectRepository.findAll();
        List<ProjectDTO> projectDTOList = new ArrayList<>();

        for(Project project : projectList){

           ProjectDTO projectDTO= modelMapper.map(project, ProjectDTO.class);
           projectDTOList.add(projectDTO);
        }

        return projectDTOList;
    }

    public List<UserDTO> getMembers(Long projectId) {

        List<User> userList = userRepository.findUsersByProjectId(projectId);
        List<UserDTO> userDTOList = new ArrayList<>();

        for(User user: userList){

            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    public void addUserToProject(Long projectId, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project Not Found"));

        project.getMembers().add(user);
        projectRepository.save(project);

    }

    public ProjectDetailsDTO getProjectDetails(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<Ticket> tickets = ticketRepository.findByProjectId(projectId);
        List<TicketDTO> ticketDTOList = new ArrayList<>();

        List<User> users = userRepository.findUsersByProjectId(projectId);
        List<UserDTO> userDTOList = new ArrayList<>();

        for( Ticket ticket : tickets){
            TicketDTO ticketDTO = modelMapper.map(ticket, TicketDTO.class);
            ticketDTOList.add(ticketDTO);
        }

        for( User user: users){
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOList.add(userDTO);
        }

        ProjectDetailsDTO projectDetailsDTO = modelMapper.map(project, ProjectDetailsDTO.class);
        projectDetailsDTO.setMembers(userDTOList);
        projectDetailsDTO.setTickets(ticketDTOList);

        return  projectDetailsDTO;
    }

    public ProjectDTO assignProjectToArea( Long projectId, Long areaId){

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));

        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));

        project.setArea(area);
        projectRepository.save(project);

        return modelMapper.map(project, ProjectDTO.class);
    }

}
