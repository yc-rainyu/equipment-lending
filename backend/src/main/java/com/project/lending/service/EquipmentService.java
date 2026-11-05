package com.project.lending.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.lending.model.Equipment;
import com.project.lending.model.EquipmentStatus;
import com.project.lending.model.EquipmentType;
import com.project.lending.repository.EquipmentRepository;
import com.project.lending.dto.EquipmentRequest;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> findAll(EquipmentType type, EquipmentStatus status) {
        return equipmentRepository.findAll(type, status);
    }

    public void create(EquipmentRequest request) {
        equipmentRepository.create(request.name(), request.type());
    }

    public void update(long id, EquipmentRequest request) {
        equipmentRepository.update(id, request.name(), request.type());
    }

    public void delete(long id) {
        equipmentRepository.delete(id);
    }
}
