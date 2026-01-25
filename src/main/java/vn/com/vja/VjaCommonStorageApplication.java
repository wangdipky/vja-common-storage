package vn.com.vja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class VjaCommonStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(VjaCommonStorageApplication.class, args);
    }

}