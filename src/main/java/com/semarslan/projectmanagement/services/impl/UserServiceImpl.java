package com.semarslan.projectmanagement.services.impl;

import com.semarslan.projectmanagement.entities.User;
import com.semarslan.projectmanagement.exceptions.UsernameAlreadyExistsException;
import com.semarslan.projectmanagement.repositories.UserRepository;
import com.semarslan.projectmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        try{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setUsername(user.getUsername());
            user.setConfirmPassword("");
            return userRepository.save(user);
        }catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '"+user.getUsername()+ "' already exists") ;
        }
    }
}
