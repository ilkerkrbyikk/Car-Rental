package com.Ilker.entitiy;

import com.Ilker.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    //private Date dob;
    @Column(nullable = false)
    private String address;
    @Column(length = 11, nullable = false)
    private String phoneNumber;

    private boolean enabled;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "verification_expiration")
    private LocalDateTime verificationCodeExpiration;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<CreditCart> creditCarts;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
