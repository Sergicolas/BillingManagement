package com.scz.BillingManagement.Model;

import lombok.Getter;

@Getter
public enum BillStatus {
    PENDING("Pending"), RECEIVED("Received");

    private String description;

    BillStatus(String description){
        this.description = description;
    }
}
