package com.Ilker.service.user;

import com.Ilker.entitiy.User;
import com.Ilker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
