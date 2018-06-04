package assignment.web.dto;

import assignment.dao.models.MyUser;
import assignment.dao.models.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import java.util.*;

/**
 * Created by kek5 on 5/3/18.
 */
public class SignUpUserDTO extends ResourceSupport{
    @JsonIgnore
    @Getter
    @Setter
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Getter
    @JsonIgnore
    private static final Set<Role> roles = new HashSet<>(Collections.singletonList(new Role("USER")));

    @Getter
    @Setter
    @NotBlank(message = "Please, enter your username")
    private String username;

    @Getter
    @NotBlank(message = "Please, enter your password")
    private String password;


    public SignUpUserDTO(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }



    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }


}
