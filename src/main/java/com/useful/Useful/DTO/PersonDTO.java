package com.useful.Useful.DTO;

import com.useful.Useful.entity.Roles;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PersonDTO {
    private String username;
    private String password;
    private List<Roles> roles;
}
