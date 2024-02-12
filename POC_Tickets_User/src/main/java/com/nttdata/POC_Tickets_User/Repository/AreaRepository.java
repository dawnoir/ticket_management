package com.nttdata.POC_Tickets_User.Repository;

import com.nttdata.POC_Tickets_User.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
}
