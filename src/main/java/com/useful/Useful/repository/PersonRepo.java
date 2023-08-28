package com.useful.Useful.repository;

import com.useful.Useful.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Long> {
    boolean existsPersonByUsername(String username);

    Person findPersonByUsername(String username);
    List<Person> getAllByIdGreaterThan(Long greaterThan);
}
