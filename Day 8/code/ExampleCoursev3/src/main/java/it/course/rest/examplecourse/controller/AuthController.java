package it.course.rest.examplecourse.controller;

import it.course.rest.examplecourse.business.interfaces.UserBO;
import it.course.rest.examplecourse.business.interfaces.RoleBO;
import it.course.rest.examplecourse.model.ERole;
import it.course.rest.examplecourse.model.Role;
import it.course.rest.examplecourse.model.User;
import it.course.rest.examplecourse.payload.request.LoginRequest;
import it.course.rest.examplecourse.payload.request.SignupRequest;
import it.course.rest.examplecourse.payload.response.MessageResponse;
import it.course.rest.examplecourse.payload.response.UserInfoResponse;
import it.course.rest.examplecourse.security.jwt.JwtUtils;
import it.course.rest.examplecourse.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  UserBO userBO;

  @Autowired
  RoleBO roleBO;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new UserInfoResponse(userDetails.getId(),
                                   userDetails.getUsername(),
                                   userDetails.getEmail(),
                                   roles));
  }
  @PostMapping("/signup/mod")
  public ResponseEntity<?>registerMod(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userBO.existUser(signUpRequest)){
      Map<String,String> message= new HashMap<>();
      message.put("error","Username is already taken!");
      return new ResponseEntity<Map<String,String>>(message,HttpStatus.BAD_REQUEST);
    }
    if (userBO.existUser(signUpRequest)) {
      Map<String,String> message= new HashMap<>();
      message.put("error","Email is already taken!");
      return new ResponseEntity<Map<String,String>>(message,HttpStatus.BAD_REQUEST);
    }
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));
    Role modRole = roleBO.getRole(ERole.ROLE_MODERATOR);
    userBO.addUserWithRole(user,modRole);
    Map<String,String> message= new HashMap<>();
    message.put("Operazione","Completata");
    return new ResponseEntity<>(message, HttpStatus.CREATED);
  }

  @PostMapping("/signup/admin")
  public ResponseEntity<?>registerAdmin(@Valid @RequestBody SignupRequest signUpRequest){
    if (userBO.existUser(signUpRequest)) {
      Map<String,String> message= new HashMap<>();
      message.put("error","Username is already taken!");
      return new ResponseEntity<Map<String,String>>(message,HttpStatus.BAD_REQUEST);
    }
    if (userBO.existUser(signUpRequest)) {
      Map<String,String> message= new HashMap<>();
      message.put("error","Email is already taken!");
      return new ResponseEntity<Map<String,String>>(message,HttpStatus.BAD_REQUEST);
    }
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));
    Role modRole = roleBO.getRole(ERole.ROLE_ADMIN);
    userBO.addUserWithRole(user,modRole);
    Map<String,String> message= new HashMap<>();
    message.put("Operazione","Completata");
    return new ResponseEntity<>(message, HttpStatus.CREATED);

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userBO.existUser(signUpRequest)) {
      Map<String,String> message= new HashMap<>();
      message.put("error","Username is already taken!");
      return new ResponseEntity<Map<String,String>>(message,HttpStatus.BAD_REQUEST);
    }
    if (userBO.existUser(signUpRequest)) {
      Map<String,String> message= new HashMap<>();
      message.put("error","Email is already taken!");
      return new ResponseEntity<Map<String,String>>(message,HttpStatus.BAD_REQUEST);
    }
    User user = new User(signUpRequest.getUsername(),
                         signUpRequest.getEmail(),
                         encoder.encode(signUpRequest.getPassword()));
    Role modRole = roleBO.getRole(ERole.ROLE_USER);
    userBO.addUserWithRole(user,modRole);
    Map<String,String> message= new HashMap<>();
    message.put("Operazione","Completata");
    return new ResponseEntity<>(message, HttpStatus.CREATED);
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    Map<String,String> message= new HashMap<>();
    message.put("Operazione","Completata");
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(message);
  }
}
