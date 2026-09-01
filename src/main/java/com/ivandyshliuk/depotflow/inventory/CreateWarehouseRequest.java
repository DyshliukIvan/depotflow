package com.ivandyshliuk.depotflow.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateWarehouseRequest(
        @NotBlank @Size(max = 64) String code,
        @NotBlank String name
) {
}
