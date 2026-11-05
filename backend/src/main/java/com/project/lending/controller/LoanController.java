package com.project.lending.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.lending.dto.BorrowRequest;
import com.project.lending.service.LoanService;

import jakarta.validation.Valid;

@RestController
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/api/equipment/{id}/borrow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrow(@PathVariable long id, @RequestBody @Valid BorrowRequest request) {
        loanService.borrow(id, request);
    }

    @PostMapping("/api/equipment/{id}/return")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnEquipment(@PathVariable long id) {
        loanService.returnEquipment(id);
    }
}
