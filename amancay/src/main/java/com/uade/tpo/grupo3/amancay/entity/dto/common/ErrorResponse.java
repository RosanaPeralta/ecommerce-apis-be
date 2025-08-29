package com.uade.tpo.grupo3.amancay.entity.dto.common;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(String status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
