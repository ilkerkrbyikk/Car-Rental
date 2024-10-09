package com.Ilker.service.credit_cart;

import com.Ilker.entitiy.CreditCart;
import com.Ilker.request.CreateCreditCartRequest;

import java.util.List;

public interface CreditCartService {

    List<com.Ilker.entitiy.CreditCart> getAll();
    CreditCart add(CreateCreditCartRequest request);
    CreditCart getById(Long creditCartId);
    void delete(Long creditCartId);

}
