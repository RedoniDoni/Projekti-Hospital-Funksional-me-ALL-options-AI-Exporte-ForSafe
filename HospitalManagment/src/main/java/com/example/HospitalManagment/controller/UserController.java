package com.example.HospitalManagment.controller;

import com.example.HospitalManagment.common.ResponseObject;
import com.example.HospitalManagment.data.RegisterRequestForAllEntityDTO;
import com.example.HospitalManagment.data.user.UserDto;
import com.example.HospitalManagment.data.user.UserView;
import com.example.HospitalManagment.security.auth.AuthenticationResponse;
import com.example.HospitalManagment.security.auth.AuthenticationService;
import com.example.HospitalManagment.security.auth.RegisterRequest;
import com.example.HospitalManagment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Log4j2
@RequestMapping("${base.url}/users")
@Controller
@RestController


public class UserController {
    private final UserService userService;
    private final AuthenticationService service;

    public UserController(UserService userService, AuthenticationService service){
        this.userService = userService;
        this.service = service;
    }

    //get all
    @Operation(summary = "Get all users", description = "Returns all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserView.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @GetMapping("/all")
    public ResponseEntity getAllUsers(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam(required = false, name = "search") String search) {
        String methodName = "getAllTickets";

        log.info("{} -> Get all Users", methodName);
        ResponseObject responseObject = userService.getAllUsers(page, size, search);

        log.info("{} -> Get all Users, response status: {}",methodName, responseObject.getCode());
        return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> createUser(
            @RequestBody RegisterRequestForAllEntityDTO request
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @Operation(summary = "Get my profile", description = "Returns the profile details of the authenticated user")
    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserView.class)))
    @GetMapping("/me")
        public ResponseEntity<UserView> getMyProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String methodName = "getMyProfile";

        log.info("{} -> Get My Profile", methodName);
        UserView userView = userService.myProfile(userDetails);

        log.info("{} -> Get user, response status: 200", methodName);

        return ResponseEntity.status(HttpStatus.OK).body(userView);
    }
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody AuthenticationRequest request
//    ){
//        return ResponseEntity.ok(service.authentication(request));
//    }

    //create
//    @Operation(summary = "Create User", description = "")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "User created",
//                    content = @Content(schema = @Schema(implementation = UserCreate.class))),
//            @ApiResponse(responseCode = "400", description = "Invalid input"),
//            @ApiResponse(responseCode = "409", description = "Conflict") })
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/create")
//    public ResponseEntity createUser(@RequestBody @Valid UserCreate userCreate) {
//        String methodName = "createUser";
//
//        log.info("{} -> Create User", methodName);
//        ResponseObject responseObject = userService.createUser(userCreate);
//
//        log.info("{} -> Create User, response status: {}",methodName, responseObject.getCode());
//        return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
//    }

    //delete
    @Operation(summary = "Delete User", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delete created",
                    content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Conflict") })
    @PostMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody @Valid Long id) {
        String methodName = "createClient";

        log.info("{} -> Create Client", methodName);
        ResponseObject responseObject = userService.deleteUser(id);

        log.info("{} -> Create Client, response status: {}",methodName, responseObject.getCode());
        return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
    }

    @Operation(summary = "Edit User", description = "Edits the details of an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserView.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PutMapping("/edit/{userId}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserView> editUser(
            @RequestBody @Valid RegisterRequest request,
            @PathVariable Long userId
    ) {
        String methodName = "editUser";

        log.info("{} -> Edit User", methodName);

        UserView editedUser = userService.editUser(request, userId);
        log.info("{} -> Edit User, response status: 200", methodName);
        return ResponseEntity.status(HttpStatus.OK).body(editedUser);
    }

    @Operation(summary = "Edit own profile", description = "Allows a user to edit their own profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = UserView.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PutMapping("/edit-self")
    public ResponseEntity<UserView> editSelf(@RequestBody @Valid RegisterRequest registerRequest,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        UserView editedUser = userService.editSelf(registerRequest, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(editedUser);
    }
}
