package assignment.services.impl;

import assignment.dao.models.MyUser;
import assignment.dao.repositories.UserRepository;
import assignment.web.dto.SignUpUserDTO;
import assignment.web.errors.exceptions.AlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kek5 on 5/15/18.
 */
@Service
public class SignUpService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SignUpService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public void SignUpNewUser(SignUpUserDTO signUpUserDTO) {
        this.checkUsername(signUpUserDTO.getUsername());

        MyUser newMyUser = modelMapper.map(signUpUserDTO, MyUser.class);
        userRepository.save(newMyUser);
    }

    private void checkUsername(String username) throws AlreadyExistsException {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new AlreadyExistsException("User","username", user.getUsername());
        });
    }
}
