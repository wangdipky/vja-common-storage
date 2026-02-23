package vn.com.vja.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* AuthResultDto
* @author QuangDK.
*/

@Getter
@Setter
@NoArgsConstructor
public class AuthResultDto {

    private String accessToken;

    private Long exprDate;
}