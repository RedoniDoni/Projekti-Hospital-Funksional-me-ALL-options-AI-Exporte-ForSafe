package com.example.HospitalManagment.security.service;

import com.example.HospitalManagment.entity.Doctor;
import com.example.HospitalManagment.entity.Nurse;
import com.example.HospitalManagment.entity.Patient;
import com.example.HospitalManagment.entity.User;
import com.example.HospitalManagment.repository.DoctorRepository;
import com.example.HospitalManagment.repository.NurseRepository;
import com.example.HospitalManagment.repository.PatientRepository;
import com.example.HospitalManagment.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service

public class JwtService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final NurseRepository nurseRepository;
    private final DoctorRepository doctorRepository;

    public JwtService(UserRepository userRepository, PatientRepository patientRepository, NurseRepository nurseRepository, DoctorRepository doctorRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.nurseRepository = nurseRepository;
        this.doctorRepository = doctorRepository;
    }

    @Value("${jwt.secret}")
    private String secret;
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(User userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }



    public String generateToken(Map<String, Object> extraClaims, User userDetails){
        if(userDetails.getRole().getName().equals("PATIENT")){
            Optional<Patient> patient =patientRepository.findByEmail(userDetails.getEmail());
            patient.ifPresent(value -> extraClaims.put("userId", value.getId()));
        }
        if(userDetails.getRole().getName().equals("NURSE")){
            Optional<Nurse> nurse =nurseRepository.findByEmail(userDetails.getEmail());
            nurse.ifPresent(value -> extraClaims.put("userId", value.getId()));
        }
        if(userDetails.getRole().getName().equals("DOCTOR")){
            Optional<Doctor> doctor =doctorRepository.findByEmail(userDetails.getEmail());
            doctor.ifPresent(value -> extraClaims.put("userId", value.getId()));
        }
        if(userDetails.getRole().getName().equals("ADMIN")){
            extraClaims.put("userId",userDetails.getId());

        }
        extraClaims.put("email",userDetails.getEmail());
        extraClaims.put("firsName",userDetails.getFirstName());
        extraClaims.put("role",userDetails.getRole().getName());

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .claim("authorities", userDetails.getAuthorities())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver
                .apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public User getCurrentUser() {
        // Get the authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If the user is authenticated, retrieve the user details
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();

                // Fetch the User from the database based on the extracted username
                Optional<User> user = userRepository.findByUsername(username);

                // Return the user or handle user not found scenario
                return user.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
            }
        }

        // Handle case where authentication or user details are not available
        throw new IllegalStateException("User not authenticated");
    }

}