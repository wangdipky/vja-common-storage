package vn.com.vja.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.com.vja.dto.AuthDto;
import vn.com.vja.dto.AuthResultDto;
import vn.com.vja.jwt.JwtConfig;
import vn.com.vja.service.IAuthService;

/**
* AuthServiceImpl
* @author QuangDK.
*/
@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements IAuthService {

    private final JwtConfig jwtConfig;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResultDto login(AuthDto authDto) {

        UsernamePasswordAuthenticationToken authInfo = new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authInfo);

        // Verify success
        if(authentication.isAuthenticated()) {

            AuthResultDto result = new AuthResultDto();

            // Generate token
            String token = jwtConfig.generateToken(authDto.getUsername(), null);
            result.setAccessToken(token);

            return result;
        }
        throw new BadCredentialsException("Incorrect username or password");
    }
}
