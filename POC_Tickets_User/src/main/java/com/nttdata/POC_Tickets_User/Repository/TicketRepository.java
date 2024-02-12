package com.nttdata.POC_Tickets_User.Repository;

import com.nttdata.POC_Tickets_User.entities.Project;
import com.nttdata.POC_Tickets_User.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t where t.priority = :priority and t.project.id = :projectId")
    List<Ticket> findByPriority(String priority, Long projectId);

    @Query("SELECT t FROM Ticket t where t.status = :status and t.status = :projectId")
    List<Ticket> findByStatus(String status, Long projectId);

    List<Ticket> findByProjectId(Long projectId);

}
