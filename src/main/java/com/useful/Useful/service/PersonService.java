package com.useful.Useful.service;

import com.useful.Useful.DTO.PersonDTO;
import com.useful.Useful.entity.Person;
import com.useful.Useful.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepo repo;

    public boolean savePerson(PersonDTO personDTO){
        try{
            if(repo.existsPersonByUsername(personDTO.getUsername())){
                return false;
            }

            Person person = new Person();
            person.setUsername(personDTO.getUsername());
            person.setPassword(personDTO.getPassword());
            person.setRoles(personDTO.getRoles());
            repo.save(person);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
