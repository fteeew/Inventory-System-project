package com.example.user.project;

import java.util.Date;

public class Payment {
    private int PaymentID;
    private double Amount;
    private int pt_CustomerID;
    private int pt_voidInd;
    private String PaymentDate;

    public Payment() {

    }

    public Payment(String PaymentDate, double Amount, int pt_CustomerID) {
        this.PaymentDate = PaymentDate;
        this.Amount = Amount;
        this.pt_CustomerID = pt_CustomerID;


    }

    public int getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(int paymentID) {
        PaymentID = paymentID;
    }


    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public int getPt_CustomerID() {
        return pt_CustomerID;
    }

    public void setPt_CustomerID(int pt_CustomerID) {
        this.pt_CustomerID = pt_CustomerID;
    }

    public int getPt_voidInd() {
        return pt_voidInd;
    }

    public void setPt_voidInd(int pt_voidInd) {
        this.pt_voidInd = pt_voidInd;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }
}
