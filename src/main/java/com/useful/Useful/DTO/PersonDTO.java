package com.useful.Useful.DTO;

import com.useful.Useful.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonDTO {
    private String username;
    private String password;
    private Role role;
}
