package com.Chan.InventoryService.DataTransferObjects;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private int statuscode;
    private String message;
    private LocalDateTime currentTime;
}
