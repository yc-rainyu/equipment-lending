package com.project.lending.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lending.model.Equipment;
import com.project.lending.model.EquipmentStatus;
import com.project.lending.model.EquipmentType;
import com.project.lending.service.EquipmentService;

@RestController
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/api/equipment")
    public List<Equipment> findAll(
            @RequestParam(required = false) EquipmentType type,
            @RequestParam(required = false) EquipmentStatus status) {
        return equipmentService.findAll(type, status);
    }
}
