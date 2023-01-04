package it.course.rest.examplecourse.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
 
public class SignupRequest {
    @NotBlank
    @Getter
    @Setter
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Getter
    @Setter
    @Size(max = 50)
    @Email
    private String email;

    @Setter
    @Getter
    private Set<String> role;
    
    @NotBlank
    @Getter
    @Setter
    @Size(min = 6, max = 40)
    private String password;
}
