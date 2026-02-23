package vn.com.vja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* AuthDto
* @author QuangDK.
*/
@Getter
@Setter
@NoArgsConstructor
public class AuthDto {

    private String username;

    private String password;
}