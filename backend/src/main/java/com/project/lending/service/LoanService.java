package com.project.lending.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lending.dto.BorrowRequest;
import com.project.lending.model.LoanRecord;
import com.project.lending.repository.LoanRepository;


@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Transactional
    public void borrow(long equipmentId, BorrowRequest request) {
        loanRepository.borrow(equipmentId, request.borrowerName());
    }

    @Transactional
    public void returnEquipment(long equipmentId) {
        loanRepository.returnEquipment(equipmentId);
    }

    public List<LoanRecord> findAll(Long equipmentId) {
        return loanRepository.findAll(equipmentId);
    }
}
