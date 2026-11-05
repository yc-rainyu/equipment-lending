package com.project.lending.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.project.lending.model.LoanRecord;

@Repository
public class LoanRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<LoanRecord> rowMapper = (resultSet, rowNum) -> new LoanRecord(
        resultSet.getLong("id"),
        resultSet.getLong("equipment_id"),
        resultSet.getString("equipment_name"),
        resultSet.getString("borrower_name"),
        resultSet.getObject("borrowed_at", OffsetDateTime.class),
        resultSet.getObject("returned_at", OffsetDateTime.class));

    public LoanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void borrow(long equipmentId, String borrowerName) {
        jdbcTemplate.update("CALL sp_borrow_equipment(?, ?)", equipmentId, borrowerName);
    }

    public void returnEquipment(long equipmentId) {
        jdbcTemplate.update("CALL sp_return_equipment(?)", equipmentId);
    }

    public List<LoanRecord> findAll(Long equipmentId) {
        return jdbcTemplate.query("SELECT * FROM fn_list_loan_records(?)", rowMapper, equipmentId);
    }
}
