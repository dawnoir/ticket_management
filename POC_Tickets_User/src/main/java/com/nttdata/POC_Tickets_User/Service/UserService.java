package com.nttdata.POC_Tickets_User.Service;


import com.nttdata.POC_Tickets_User.DTOs.UserDTO;
import com.nttdata.POC_Tickets_User.Repository.UserRepository;
import com.nttdata.POC_Tickets_User.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO createUser(UserDTO userDTO){

        User user = modelMapper.map(userDTO, User.class);
        User createdUser = userRepository.save(user);

        return modelMapper.map(createdUser, UserDTO.class);
    }

    public boolean deleteUser(Long id){

        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return !userRepository.existsById(id);
        } else {
            return false;
        }

    }

    public List<UserDTO> getAllUsers(){

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
