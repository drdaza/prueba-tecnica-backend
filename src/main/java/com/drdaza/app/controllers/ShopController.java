package com.drdaza.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drdaza.app.models.Purchase;
import com.drdaza.app.services.actionsService.ShopService;

@RestController
@RequestMapping(path = "/api/shop")
public class ShopController {

    private ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    @PostMapping("/course/{idCourse}/paymethod/{idMethod}/profile/{idPorfile}")
    public ResponseEntity<Map<String, String>> shopCourse(@PathVariable Long idCourse, @PathVariable Long idMethod,
            @PathVariable Long idProfile) {
        try {

            Purchase purchaseDB = service.shopCourse(idCourse, idMethod, idProfile);

            Map<String, String> json = new HashMap<>();

            json.put("message", "succesfully");
            json.put("id_purchase", purchaseDB.getId().toString());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(json);
        } catch (Exception e) {
            Map<String, String> json = new HashMap<>();

            json.put("Error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

    }
}
