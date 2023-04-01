package com.drdaza.app.controllers.securityControllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class AuthController {

    @GetMapping(value = "/login")
    public ResponseEntity<Map<String, String>> acces() {

        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();

            Map<String, String> json = new HashMap<>();

            json.put("message", "Logged");
            json.put("username", auth.getName());
            json.put("role", auth.getAuthorities().iterator().next().toString());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(json);
        } catch (Exception e) {
            Map<String, String> json = new HashMap<>();

            json.put("message", "Problen to login");
            json.put("Error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

    }
}
