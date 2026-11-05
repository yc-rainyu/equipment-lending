package com.project.lending.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoanRepository {

    private final JdbcTemplate jdbcTemplate;

    public LoanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void borrow(long equipmentId, String borrowerName) {
        jdbcTemplate.update("CALL sp_borrow_equipment(?, ?)", equipmentId, borrowerName);
    }

    public void returnEquipment(long equipmentId) {
        jdbcTemplate.update("CALL sp_return_equipment(?)", equipmentId);
    }
}
