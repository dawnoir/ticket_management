package com.nttdata.POC_Tickets_User.Controller;

import com.nttdata.POC_Tickets_User.DTOs.UserDTO;
import com.nttdata.POC_Tickets_User.Service.UserService;
import com.nttdata.POC_Tickets_User.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO){

        UserDTO createdUser = userService.createUser(userDTO);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){

        List<UserDTO> userDTOList = userService.getAllUsers();

        return ResponseEntity.ok(userDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long userId){

        if(userService.deleteUser(userId)) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
