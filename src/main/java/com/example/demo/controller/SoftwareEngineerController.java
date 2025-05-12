package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SoftwareEngineer;
import com.example.demo.service.SoftwareEngineerService;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineer> getEngineers() {
        return softwareEngineerService.getAllSoftwareEngineers();
    } 

    @GetMapping("{id}")
    public SoftwareEngineer getEngineersbyId(@PathVariable Integer id) {
        return softwareEngineerService.getSoftwareEngineerById(id);
    } 

    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer) {
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateSoftwareEngineer(
            @PathVariable Integer id,
            @RequestBody SoftwareEngineer updatedEngineer) {

        boolean updated = softwareEngineerService.updateSoftwareEngineer(id, updatedEngineer);

        if (updated) {
            return ResponseEntity.ok("Software engineer updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Software engineer not found.");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSoftwareEngineerByName(@RequestBody Map<String, String> request) {
        String name = request.get("name");

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name must be provided in the request body.");
        }

        boolean deleted = softwareEngineerService.deleteSoftwareEngineerByName(name);

        if (deleted) {
            return ResponseEntity.ok("Software engineer '" + name + "' deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Software engineer '" + name + "' not found.");
        }
    }
}
