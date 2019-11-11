package ro.danielstn.stockalarms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.danielstn.stockalarms.datasource.User;
import ro.danielstn.stockalarms.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO newUser) {
        User createdUser = userService.createUser(
                new User()
                        .setName(newUser.getName())
                        .setEmail(newUser.getEmail())
                        .setPassword(newUser.getPwd())
        );
        if(createdUser == null) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO response = new UserDTO().setEmail(createdUser.getEmail()).setName(createdUser.getName()).setPwd("****");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
