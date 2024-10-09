package com.Ilker.service.auth;

import com.Ilker.dto.VerifyUserDto;
import com.Ilker.entitiy.User;
import com.Ilker.enums.Role;
import com.Ilker.repository.UserRepository;
import com.Ilker.request.LoginRequest;
import com.Ilker.request.RegisterRequest;
import com.Ilker.service.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;


    public User signUp(RegisterRequest request){
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAddress(request.getAddress());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(Role.CUSTOMER);
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(15));
        user.setEnabled(false);
        sendVerificationEmail(user);

        return userRepository.save(user);
    }

    public User authenticate(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        if(!user.isEnabled()){
            //TODO: Exception yaz.
            throw new RuntimeException("Account not verified yet. Please verify your account.");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),request.getPassword()
        ));

        return user;
    }

    public void verifyUser(VerifyUserDto userDto){
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getVerificationCodeExpiration().isBefore(LocalDateTime.now())){
                //TODO: Exception yaz.
                throw new RuntimeException("Verification code has expired.");
            }
            if(user.getVerificationCode().equals(userDto.getVerificationCode())){
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiration(null);
                userRepository.save(user);
            }else {
                throw new RuntimeException("Invalid verification code...");
            }
        }else{
            throw new UsernameNotFoundException("User not found.");
        }
    }

    public void resendVerificationCode(String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.isEnabled()){
                throw new RuntimeException("Account is already verified.");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiration(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(user);
            userRepository.save(user);
        }else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    public void sendVerificationEmail(User user){
        String subject = "Account Verification";
        String verificationCode = user.getVerificationCode();

        //TODO: BURAYA MAILDE GÖZÜKECEK TEMİZ GÜZEL BİR HTML BUL.
        String htmlMessage = "";


        try {
            emailService.sendVerificationMail(user.getEmail(),subject,htmlMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String generateVerificationCode(){
        Random random = new Random();
        int code = random.nextInt(900000) + 10000;
        return String.valueOf(code);
    }
}
