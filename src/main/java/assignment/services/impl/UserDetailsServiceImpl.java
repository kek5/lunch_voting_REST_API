package assignment.services.impl;


import assignment.dao.models.MyUser;
import assignment.dao.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.Optional;

/**
 * Created by kek5 on 4/30/18.
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Authentication {}:", s);

        Optional<MyUser> optional = userRepository.findByUsername(s);

        return optional
                .map(MyUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s was not found", s)));
    }
}
