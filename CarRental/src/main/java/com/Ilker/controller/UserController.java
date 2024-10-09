package com.Ilker.controller;

import com.Ilker.entitiy.User;
import com.Ilker.repository.UserRepository;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll(){
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(new ApiResponse("Success.", users));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse> authenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new ApiResponse("",currentUser));
    }

}
