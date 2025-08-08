package com.example.HospitalManagment.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.BaseDTO;
import com.example.HospitalManagment.data.RegisterRequestForAllEntityDTO;
import com.example.HospitalManagment.data.user.*;
import com.example.HospitalManagment.entity.User;
import com.example.HospitalManagment.enums.Entity;
import com.example.HospitalManagment.enums.Status;
import com.example.HospitalManagment.exception.*;
import com.example.HospitalManagment.repository.NativeQueryRepository;
import com.example.HospitalManagment.repository.RoleRepository;
import com.example.HospitalManagment.repository.UserRepository;
import com.example.HospitalManagment.security.auth.RegisterRequest;
import com.example.HospitalManagment.security.service.JwtService;
import com.example.HospitalManagment.security.auth.AuthenticationResponse;
import jakarta.mail.MessagingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ValidationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Log4j2
public class UserService extends BaseService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    //private final StorageService storageService;
//    private final CategoryRepository categoryRepository;
//    private final SubCategoryRepository subCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final NativeQueryRepository nativeQueryRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final NurseService nurseService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService, NativeQueryRepository nativeQueryRepository, DoctorService doctorService, PatientService patientService, NurseService nurseService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.nativeQueryRepository = nativeQueryRepository;

        this.doctorService = doctorService;
        this.patientService = patientService;
        this.nurseService = nurseService;
    }

    public AuthenticationResponse createUser(RegisterRequestForAllEntityDTO request) throws MessagingException, IOException {
        validateRegisterRequest(request);

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("A user with this email already exists: " + request.getEmail());
        }

        String logoUrl = null;
        var user = com.example.HospitalManagment.entity.User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.findByName(request.getRole().name()))
                .phoneNumber(request.getPhoneNumber())
                .imageUrl(logoUrl)
                .status(Status.ACTIVE)
                .build();

        userRepository.save(user);
        if(request.getRole().equals(Entity.DOCTOR)){
            doctorService.createDoctor(request);
        }
        if(request.getRole().equals(Entity.PATIENT)){
            patientService.createPatient(request);
        }
        if(request.getRole().equals(Entity.NURSE)){
            nurseService.createNurse(request);
        }
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .build();
    }



    private void validateRegisterRequest(RegisterRequestForAllEntityDTO request) {
        if (request.getFirstName() == null || request.getFirstName().isEmpty() ||
                request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new ValidationException("Please fill the required  fields");
        }
    }

    // get all users
    public ResponseObject getAllUsers(Integer page, Integer size, String search) {
        String methodName = "getAllUSers";

        log.info("{} -> Get all Users", methodName);
        ResponseObject responseObject = new ResponseObject();
        try {
            List<UserView> userList = null;

            userList = nativeQueryRepository.getUsers(page, size, search);

            Long count = nativeQueryRepository.getUsersCount(search);

            Pageable sortedById = PageRequest.of(page - 1, size);
            Page<UserView> resultPage = new PageImpl<>(userList, sortedById,
                    count);

            responseObject.setData(mapPage(resultPage, userList));
            responseObject.prepareHttpStatus(HttpStatus.OK);
        } catch (Exception e) {
            log.error("{} -> Get all Users", methodName);
            throw new InternalException(e.getLocalizedMessage(), e.getCause());
        }
        log.info("{} -> Get all Users, response status: {}", methodName, responseObject.getCode());
        return responseObject;
    }
    // delete user
    public ResponseObject deleteUser(Long id) {
        String methodName = "deleteUser";

        log.info("{} -> Delete User", methodName);
        ResponseObject responseObject = new ResponseObject();

        try {
            User userToDelete = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id :"+id));
            userToDelete.setDeletedAt(LocalDateTime.now());
                userRepository.save(userToDelete);
                responseObject.setData("User deleted successfully.");
                responseObject.prepareHttpStatus(HttpStatus.OK);

        } catch (Exception e) {
            log.error("{} -> Delete User", methodName);
            throw new InternalException(e.getLocalizedMessage(), e.getCause());
        }

        log.info("{} -> Delete User, response status: {}", methodName, responseObject.getCode());
        return responseObject;
    }

    public UserView myProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String methodName = "getUser";

        log.info("{} -> Get User by Id", methodName);

        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());

        try {
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                return UserView.fromEntity(user);
            } else {
                throw new NotFoundApiException(ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            log.error("{} -> Get User by Id", methodName);
            throw new InternalException(e.getLocalizedMessage(), e.getCause());
        }
    }

    // edit user from admin
    public UserView editUser(RegisterRequest request, Long userId) {
        try {
            //validateRegisterRequest(request);

            // Retrieve the existing user
            User existingUser = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("User not found"));

            // Update the user fields based on the request
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            existingUser.setPhoneNumber(request.getPhoneNumber());
            existingUser.setImageUrl(request.getImageUrl());
            existingUser.setStatus(request.getStatus());
            userRepository.save(existingUser);

            return UserView.fromEntity(existingUser);

        } catch (ValidationException e) {
            throw new ValidationException("Validation error");
        } catch (NotFoundException e) {
            throw new NotFoundException("User not found");
        } catch (Exception e) {
            log.error("Error editing user", e);
            throw new InternalException(e.getLocalizedMessage(), e.getCause());
        }
    }

    // edit user itself

    public UserView editSelf(RegisterRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            validateSelfEditRequest(request, userDetails);

            User existingUser = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new NotFoundException("User not found"));



            // Update the user fields based on the request
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            existingUser.setEmail(request.getEmail());
            existingUser.setPhoneNumber(request.getPhoneNumber());
            existingUser.setImageUrl(request.getImageUrl());

            userRepository.save(existingUser);

            return UserView.fromEntity(existingUser);
        } catch (ValidationException e) {
            throw new ValidationException("Validation error");
        } catch (NotFoundException e) {
            throw new NotFoundException("User not found");
        } catch (Exception e) {
            log.error("Error editing user", e);
            throw new InternalException(e.getLocalizedMessage(), e.getCause());
        }
    }

    private void validateSelfEditRequest(RegisterRequest request, UserDetails currentUser) {
        if (currentUser == null) {
            throw new ValidationException("User is not authenticated.");
        }
    }
}
