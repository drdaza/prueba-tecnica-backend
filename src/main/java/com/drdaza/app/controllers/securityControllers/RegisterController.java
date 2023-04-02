package com.drdaza.app.controllers.securityControllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drdaza.app.models.User;
import com.drdaza.app.services.UserService;
import com.drdaza.app.services.intefaces.BasicService;

@RestController
@RequestMapping(path = "/api")
public class RegisterController {

    private BasicService<User> service;
    
    public RegisterController(UserService service) {
        this.service = service;
    }



    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> save(@RequestBody User user) {

        try {
            User userDB = service.Save(user);

            Map<String, String> json = new HashMap<>();

            json.put("username", userDB.getUsername());
            json.put("message", "successful sign up");
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        } catch (Exception e) {
            Map<String, String> json = new HashMap<>();

            json.put("problem", e.getMessage());
            json.put("message", "Error to sign up");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }
    }
}
