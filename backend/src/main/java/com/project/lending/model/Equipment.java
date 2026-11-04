package com.project.lending.model;

import java.time.OffsetDateTime;

public record Equipment(
    long id,
    String name,
    EquipmentType type,
    EquipmentStatus status,
    OffsetDateTime createdAt
) {
}
