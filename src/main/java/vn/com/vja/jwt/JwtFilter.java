package vn.com.vja.jwt;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
* JwtFilter
* QuangDK.
*/
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = null;
        String token = null;
        String header = request.getHeader("Authorization");

        // Check token in header and get it
        if(null != header && header.startsWith("Bearer ")) {

            token = header.substring(7);
        }

        // Extract username from token
        if(null != token) {

            username = jwtConfig.extractUsername(token);
        }

        // validate token
        if(null != token && null != username) {

            // Get user detail
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtConfig.validateToken(userDetails, token) && ObjectUtils.isEmpty(SecurityContextHolder.getContext().getAuthentication())) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // Do filter
        filterChain.doFilter(request, response);
    }
}