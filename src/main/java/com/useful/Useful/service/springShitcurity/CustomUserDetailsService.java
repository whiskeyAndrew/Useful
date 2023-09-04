package com.useful.Useful.service.springShitcurity;

import com.useful.Useful.entity.Person;
import com.useful.Useful.repository.PersonRepo;
import com.useful.Useful.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    private final PersonRepo personRepo;
    private final RoleService rolesService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findPersonByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not exists by Username");
        }
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        authoritySet.add(new SimpleGrantedAuthority(person.getRole().getName()));
        return new User(person.getUsername(), person.getPassword(), authoritySet);
    }

}
