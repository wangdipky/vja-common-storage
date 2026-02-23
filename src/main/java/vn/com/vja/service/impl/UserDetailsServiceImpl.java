package vn.com.vja.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.com.vja.entity.VjaAccount;
import vn.com.vja.entity.VjaPassword;
import vn.com.vja.repository.VjaAccountRepository;
import vn.com.vja.repository.VjaPasswordRepository;
import java.util.List;

/**
*
* QuangDK.
*/
@Service
@RequiredArgsConstructor
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private final VjaAccountRepository vjaAccountRepository;

    private final VjaPasswordRepository vjaPasswordRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Get account by Username
        VjaAccount account = vjaAccountRepository.findVjaAccountByUsernameAndDeletedDateIsNull(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER Not found"));

        // Get password
        VjaPassword password = vjaPasswordRepository.findVjaPasswordByAccountId(account.getId())
                .orElseThrow(() -> new BadCredentialsException("Invalid account"));

        // Get roles
        List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority(account.getRole()));
        return new User(account.getUsername(), password.getPassword(), roles);
    }
}
