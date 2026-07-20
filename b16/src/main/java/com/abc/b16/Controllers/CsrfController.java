package com.abc.b16.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CsrfController {

    @GetMapping("/api/csrf")
    public ResponseEntity<?> getCsrfToken(CsrfToken csrfToken) {

        Map<String, String> response = new HashMap<>();
        response.put("token", csrfToken.getToken());
        response.put("headerName", csrfToken.getHeaderName());
        response.put("parameterName", csrfToken.getParameterName());

        return ResponseEntity.ok(response);
    }
}
