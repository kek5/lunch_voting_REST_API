package assignment.web.controllers;

import assignment.config.ApiConfig;
import assignment.services.impl.SignUpService;
import assignment.web.dto.SignUpUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by kek5 on 4/30/18.
 */
@RestController
@RequestMapping((ApiConfig.API_ENTRY + ApiConfig.VERSION + "/signup"))
public class SignUpController {
    private SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SignUpUserDTO> SignUp(@RequestBody @Valid final SignUpUserDTO signUpUserDTO) {
        this.signUpService.SignUpNewUser(signUpUserDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
