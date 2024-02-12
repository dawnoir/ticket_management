package com.nttdata.POC_Tickets_User.Controller;

import com.nttdata.POC_Tickets_User.DTOs.AreaDTO;
import com.nttdata.POC_Tickets_User.Service.AreaService;
import com.nttdata.POC_Tickets_User.entities.Area;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping
    public ResponseEntity<AreaDTO> createArea(@RequestBody AreaDTO areaDTO){
        Area createdArea = areaService.createArea(areaDTO);
        return new ResponseEntity<>(areaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteArea(@RequestParam Long areaId){

        boolean deleted = areaService.deleteArea(areaId);

        if (deleted) {

            return ResponseEntity.ok("Area deleted successfully");
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
