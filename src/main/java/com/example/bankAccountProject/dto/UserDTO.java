package com.example.bankAccountProject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    @Size(min = 3, max = 20, message = "{firstName.error}")
    @NotBlank
    private String firstName;

    @Size(min = 3, max = 20, message = "{lastName.error}")
    @NotBlank
    private String lastName;

    @Email(message = "{email.error}")
    @NotBlank
    private String email;

    @Size(min = 6, max = 20, message = "{password.error}")
    @NotBlank
    private String password;
}