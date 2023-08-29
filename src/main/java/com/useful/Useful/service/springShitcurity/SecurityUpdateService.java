package com.useful.Useful.service.springShitcurity;

import com.useful.Useful.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUpdateService {
    private final CustomUserDetailsService customUserDetailsService;
    public void forceUpdateUser(Person person){
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(person.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
