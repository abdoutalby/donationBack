package com.uib.donation.stripe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeRequest {

    private int amount;
    private String currency;
    private String token = "pk_test_51PdVeuKpnFKx1MbZRGuXj7xdLT895gTmgzKmhGN07ZwZTfUpTY8gavTAq9Op0YNlpBy4aqV7JJJw4iC9K3khiRzw00m2Dl3WWW";
}
