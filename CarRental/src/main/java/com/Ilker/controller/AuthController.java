package com.Ilker.controller;

import com.Ilker.dto.VerifyUserDto;
import com.Ilker.entitiy.User;
import com.Ilker.request.LoginRequest;
import com.Ilker.request.RegisterRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.response.LoginResponse;
import com.Ilker.security.JwtService;
import com.Ilker.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody RegisterRequest request){
        User registeresUser =  authService.signUp(request);
        return ResponseEntity.ok(new ApiResponse("Success.", registeresUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request){
        User authenticatedUser = authService.authenticate(request);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken,jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto userDto){
        try {
            authService.verifyUser(userDto);
            return ResponseEntity.ok("Account verified successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email){
        try {
            authService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
