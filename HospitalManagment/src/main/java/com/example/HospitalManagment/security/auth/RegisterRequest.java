package com.example.HospitalManagment.security.auth;

import com.example.HospitalManagment.data.BaseDTO;
import com.example.HospitalManagment.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest extends BaseDTO {

    private String firstName;
    private String lastName;

    @NotBlank(message = "must.not.be.empty")
    @Email(message = "email.is.not.valid")
    private String email;

    @Size(min = 5, max =45, message = "password.min.max")
    private String password;

    private String phoneNumber;
    private String imageUrl;
    private Status status;
    private String role;
}
