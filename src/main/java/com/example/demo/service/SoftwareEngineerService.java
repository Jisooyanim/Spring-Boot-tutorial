package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.SoftwareEngineer;
import com.example.demo.repository.SoftwareEngineerRepository;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public boolean updateSoftwareEngineer(Integer id, SoftwareEngineer updatedEngineer) {
        Optional<SoftwareEngineer> existing = softwareEngineerRepository.findById(id);
        if (existing.isPresent()) {
            SoftwareEngineer engineer = existing.get();
            engineer.setName(updatedEngineer.getName());
            engineer.setTechStack(updatedEngineer.getTechStack());
            softwareEngineerRepository.save(engineer);
            return true;
        }
        return false;
    }

    public boolean deleteSoftwareEngineerByName(String name) {
        List<SoftwareEngineer> engineers = softwareEngineerRepository.findByName(name);
        if (!engineers.isEmpty()) {
            softwareEngineerRepository.deleteAll(engineers);
            return true;
        }
        return false;
    }
}