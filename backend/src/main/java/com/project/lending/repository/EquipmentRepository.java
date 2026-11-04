package com.project.lending.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.project.lending.model.Equipment;
import com.project.lending.model.EquipmentStatus;
import com.project.lending.model.EquipmentType;

@Repository
public class EquipmentRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Equipment> rowMapper = (resultSet, rowNum) -> new Equipment(
            result.getLong("id"),
            result.getString("name"),
            EquipmentType.valueOf(result.getString("type")),
            EquipmentStatus.valueOf(result.getString("status")),
            result.getObject("created_at", OffsetDateTime.class));

    public EquipmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Equipment> findAll(EquipmentType type, EquipmentStatus status) {
        return jdbcTemplate.query(
                "SELECT * FROM fn_list_equipment(?, ?)",
                rowMapper,
                type == null ? null : type.name(),
                status == null ? null : status.name());
    }
}
