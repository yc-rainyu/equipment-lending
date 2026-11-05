package com.project.lending.dto;

import com.project.lending.model.EquipmentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EquipmentRequest(
    @NotBlank(message = "請輸入設備名稱")
    @Size(max = 100, message = "設備名稱不可超過 100 字")
    String name,

    @NotNull(message = "請選擇設備類型")
    EquipmentType type
) {
}
