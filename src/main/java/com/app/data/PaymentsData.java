package com.app.data;

import java.sql.Timestamp;

public class PaymentsData {
    private int paymentId;
    private String orderNo, paymentMethod;
    private double amount;
    private Timestamp paymentDate;
    private byte paymentStatus;

    private PaymentsData() {}

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public byte getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(byte paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
