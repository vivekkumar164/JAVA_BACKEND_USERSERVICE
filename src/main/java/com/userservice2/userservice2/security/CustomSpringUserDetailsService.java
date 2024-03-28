package com.userservice2.userservice2.security;

import com.userservice2.userservice2.models.User;
import com.userservice2.userservice2.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Optional;
@Service
public class CustomSpringUserDetailsService implements UserDetailsService  {
    private UserRepository userRepository;

    public CustomSpringUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Fetch the user with the given username from DB.
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User with the given username doesn't exist");
        }

//        User user = userRepository.findByEmail(username).
//                orElseThrow(() -> {throw new UsernameNotFoundException("User with the given username doesn't exist");});

        User user = optionalUser.get();
        return new CustomUserDetails(user);
    }
}
