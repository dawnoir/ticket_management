package com.nttdata.POC_Tickets_User.Repository;

import com.nttdata.POC_Tickets_User.entities.Project;
import com.nttdata.POC_Tickets_User.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
