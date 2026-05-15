package com.Chan.InventoryService.DataTransferObjects;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InventoryRequest {

    @NotBlank(message = "ProductId is Required")
    @Pattern(
        regexp = "^PROD-[0-9]{3}$",
        message = "Product ID format must be PROD-XXX (e.g. PROD-001)"
    )
    private String productId;

    @NotBlank(message = "ProductName is Required")
    @Size(
        min = 2,
        max = 100,
        message = "ProductName must be between 2 and 100 characters"
    )
    private String productName;

    @NotNull(message = "Quantity is Required")
    @Min(value = 0, message = "Quantity cannot be Negative")
    @Max(value = 100000, message = "Quantity cannot exceed 100,000")
    private int quantity;

    @NotNull(message = "Price is Required")
    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Price cannot exceed 999,999.99")
    @Digits(
        integer = 6,
        fraction = 2,
        message = "Price format invalid (max 6 digits, 2 decimals)"
    )
    private double price;

    @NotBlank(message = "Location is Required ")
    @Size(
        min = 2,
        max = 100,
        message = "Location must be between 2 and 100 characters"
    )
    private String location;
}
