package com.example.HospitalManagment.data.user;

import com.example.HospitalManagment.data.BaseDTO;
import com.example.HospitalManagment.entity.User;
import com.example.HospitalManagment.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserView extends BaseDTO {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private Status status;
        private String role;

        public UserView(Long id, LocalDateTime createdAt, String createdBy, String firstName, String lastName, String email, String phoneNumber, Status status, String role) {
            super(id,createdAt,createdBy);
            this.firstName = firstName;
            this.lastName =lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.status=status;
            this.role = role;
        }

        public static UserView fromEntity(User user) {
            return new UserView(
                    user.getId(),
                    user.getCreatedAt(),
                    user.getCreatedBy(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getStatus(),
                    user.getRole().getName()
            );
        }
    }
