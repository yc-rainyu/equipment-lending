package com.project.lending.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BorrowRequest(
    @NotBlank(message = "請輸入借用人")
    @Size(max = 50, message = "借用人不可超過 50 字")
    @Pattern(regexp = "[^<>]*", message = "借用人不可包含 < 或 >")
    String borrowerName
) {
}
