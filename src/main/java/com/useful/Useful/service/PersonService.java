package com.useful.Useful.service;

import com.useful.Useful.DTO.PersonDTO;
import com.useful.Useful.entity.Person;
import com.useful.Useful.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepo repo;
    private final PasswordEncoder passwordEncoder;

    public boolean isPersonExistsByUsername(String username) {
        return repo.existsPersonByUsername(username);
    }

    public boolean savePerson(PersonDTO personDTO) {
        try {
            if (repo.existsPersonByUsername(personDTO.getUsername())) {
                return false;
            }

            Person person = new Person();
            person.setUsername(personDTO.getUsername());
            person.setPassword(passwordEncoder.encode(personDTO.getPassword()));
            person.setRoles(personDTO.getRoles());
            repo.save(person);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Person> getAllPerson(){
        return repo.getAllByIdGreaterThan(-1L);
    }
    public boolean isPasswordTrue(Person person, String rawPassword) {
        return passwordEncoder.matches(rawPassword, person.getPassword());
    }

    public Person findPersonByUsername(String username) {
        Person person = repo.findPersonByUsername(username);
        return repo.findPersonByUsername(username);
    }

}
