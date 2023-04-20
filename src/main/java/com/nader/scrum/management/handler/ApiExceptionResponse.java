package com.nader.scrum.management.handler;

import java.time.LocalDateTime;

public record ApiExceptionResponse(
        String path,
        String message,
        int statusCode,
        LocalDateTime localDateTime

) {
}
