package com.project.lending.model;

import java.time.OffsetDateTime;

public record LoanRecord(
    long id,
    long equipmentId,
    String equipmentName,
    String borrowerName,
    OffsetDateTime borrowedAt,
    OffsetDateTime returnedAt
) {
}
