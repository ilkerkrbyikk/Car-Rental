package com.Ilker.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 16 , unique = true, nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String cardOwner;

    @Column(length = 3, nullable = false)
    private String cardCvv;

    @Column(nullable = false)
    private String cardExpirationDate;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    private User user;



}
