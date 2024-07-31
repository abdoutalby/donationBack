package com.uib.donation.stripe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {

    private String id;
    private int amount;
    private String currency;
    private String status;
}
