package com.exalt.villaRentalSystem.dto;


import lombok.Data;

import java.util.Date;
@Data
public class BillDto {
    private Date date = new Date();
    private String description;
    private double amount;
    private int villa_id;

    public int getVilla_id() {
        return villa_id;
    }

    public void setVilla_id(int villa_id) {
        this.villa_id = villa_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
