package com.uib.donation.controllers;

import com.uib.donation.models.Checkout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    private String stripePublicKey = "stripe_key";

    @RequestMapping("/checkout")
    public String checkout(Checkout model) {
       return "checkout" ;
    }
}
