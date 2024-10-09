package com.Ilker.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditCartRequest {

    private String cartNumber;
    private String cardOwner;
    private String cardCvv;
    private String cardExpirationDate;
    private Long userId;

}
