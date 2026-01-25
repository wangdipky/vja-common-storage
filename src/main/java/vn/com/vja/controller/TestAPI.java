package vn.com.vja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.vja.jwt.JwtConfig;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestAPI {

    private final JwtConfig jwtConfig;

    @GetMapping("/hello")
    public String hello() {

        String x = jwtConfig.generateToken("HELLO", null);
        return "HELLO";
    }
}
