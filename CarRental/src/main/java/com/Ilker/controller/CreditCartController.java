package com.Ilker.controller;

import com.Ilker.entitiy.CreditCart;
import com.Ilker.exceptions.CreditCartNotFoundException;
import com.Ilker.request.CreateCreditCartRequest;
import com.Ilker.response.ApiResponse;
import com.Ilker.service.credit_cart.CreditCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/carts")
@RequiredArgsConstructor
public class CreditCartController {

    private final CreditCartService creditCartService;

    @GetMapping("/all")
    public List<CreditCart> getAll(){
        return creditCartService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCreditCart(@RequestBody CreateCreditCartRequest request) throws UsernameNotFoundException {
        CreditCart creditCart = creditCartService.add(request);
        return ResponseEntity.ok(new ApiResponse("",creditCart));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteCart(@RequestParam Long creditCartId){
        creditCartService.delete(creditCartId);
        return ResponseEntity.ok(new ApiResponse("",null ));
    }
}
