package com.abc.ktckjavaspring.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abc.ktckjavaspring.DTO.ApiResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return ResponseEntity.ok(new ApiResponse<>(
                false,
                "Lỗi: " + e.getMessage() + " | " + sw.toString(),
                null));
    }
}
