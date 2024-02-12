package com.nttdata.POC_Tickets_User.Service;

import com.nttdata.POC_Tickets_User.DTOs.AreaDTO;
import com.nttdata.POC_Tickets_User.Repository.AreaRepository;
import com.nttdata.POC_Tickets_User.entities.Area;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaService {

    @Autowired
    public AreaRepository areaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Area createArea(AreaDTO areaDTO){

        Area area = modelMapper.map(areaDTO,Area.class);
        return areaRepository.save(area);
    }

    public List<AreaDTO> getAllAreas(){

        List<Area> areas = areaRepository.findAll();

        return areas.stream().map(area -> modelMapper.map(area, AreaDTO.class)).collect(Collectors.toList());
    }

    public boolean deleteArea(Long id){

        if(areaRepository.existsById(id)){
            areaRepository.deleteById(id);
            return !areaRepository.existsById(id);
        } else {
            return false;
        }

    }
}
