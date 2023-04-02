package com.drdaza.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drdaza.app.models.Profile;
import com.drdaza.app.services.UserProfileService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api/profile")
public class UserProfileController {

    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, String>> updateProfile(@PathVariable Long id, @RequestBody Profile entity) {

        try {
            Profile profileDB = userProfileService.update(id, entity);
            Map<String, String> json = new HashMap<>();

            json.put("message", "the profile has been modified.");
            json.put("profile id", profileDB.getId().toString());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(json);
        } catch (Exception e) {
            Map<String, String> json = new HashMap<>();

            json.put("message", "error");
            json.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, String>> deleteProfile(@PathVariable Long id) {
        try {
            userProfileService.delete(id);

            Map<String, String> json = new HashMap<>();

            json.put("message", "the profile has been modified.");

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(json);
        } catch (Exception e) {
            Map<String, String> json = new HashMap<>();

            json.put("message", "error");
            json.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }
    }

    @GetMapping("")
    public List<Profile> listAll(){
        return userProfileService.listAll();
    }
}
