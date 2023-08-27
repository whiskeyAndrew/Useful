package com.useful.Useful.service;

import com.useful.Useful.entity.Person;
import com.useful.Useful.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.findPersonByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not exists by Username");
        }
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        return new User(person.getUsername(), person.getPassword(), authoritySet);
    }
}
