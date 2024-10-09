package com.Ilker.service.credit_cart;

import com.Ilker.entitiy.CreditCart;
import com.Ilker.entitiy.User;
import com.Ilker.exceptions.CreditCartNotFoundException;
import com.Ilker.repository.CreditCartRepository;
import com.Ilker.repository.UserRepository;
import com.Ilker.request.CreateCreditCartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CreditCartImpl implements CreditCartService{

    private final CreditCartRepository creditCartRepository;
    private final UserRepository userRepository;


    @Override
    public List<com.Ilker.entitiy.CreditCart> getAll() {
        return creditCartRepository.findAll();
    }

    @Override
    public CreditCart add(CreateCreditCartRequest request) throws UsernameNotFoundException{
        User user = userRepository.findUserById(request.getUserId())
                .orElseThrow();
        CreditCart creditCart = new CreditCart();
        creditCart.setUser(user);
        creditCart.setCardOwner(request.getCardOwner());
        creditCart.setCardCvv(request.getCardCvv());
        creditCart.setCardNumber(request.getCartNumber());
        creditCart.setCardExpirationDate(request.getCardExpirationDate());

        return creditCartRepository.save(creditCart);
    }

    @Override
    public CreditCart getById(Long creditCartId) throws CreditCartNotFoundException{
        return creditCartRepository.findById(creditCartId).orElseThrow();
    }

    @Override
    public void delete(Long creditCartId) throws CreditCartNotFoundException {
         checkIsExistsCreditCartById(creditCartId);
         creditCartRepository.deleteById(creditCartId);
    }

    public void checkIsExistsCreditCartById(Long cardId){
        if(!creditCartRepository.existsById(cardId)){
            throw new CreditCartNotFoundException("Credit cart not found.");
        }
    }
}
