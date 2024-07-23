package com.app.data;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.sql.Timestamp;

public class OrdersData {
    private int orderId, pickupId;
    private String orderNo;
    private String senderName, receiverName, pickupAddress, deliveryAddress, deliveryMan;
    private String senderMobileNumber, receiverMobileNumber;
    private Timestamp estimatedDeliveryDate, dateCreated, deliveredDate;
    private String status;

    public OrdersData() {
    }


    /*******************************************************************************************************************
        COMPONENT RENDERS
     *******************************************************************************************************************/
    public ComponentRenderer<Button, OrdersData> assignDeliveryManButton() {
        return new ComponentRenderer<>(dataItem -> {
            Button button = new Button("Assign", event -> {

            });
            return button;
        });
    }

    public ComponentRenderer<Component, OrdersData> cancelOrViewOrderButtons() {
        return new ComponentRenderer<>(dataItem -> {
            Button cancelButton = new Button(VaadinIcon.TRASH.create());
            Button viewButton = new Button(VaadinIcon.EDIT.create());
            VerticalLayout layout = new VerticalLayout(cancelButton, viewButton);

            return layout;
        });
    }

    public ComponentRenderer<Span, OrdersData> orderStatus() {
        return new ComponentRenderer<>(dataItem -> {
            Span span = new Span();

            return span;
        });
    }




    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPickupId() {
        return pickupId;
    }

    public void setPickupId(int pickupId) {
        this.pickupId = pickupId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getSenderMobileNumber() {
        return senderMobileNumber;
    }

    public void setSenderMobileNumber(String senderMobileNumber) {
        this.senderMobileNumber = senderMobileNumber;
    }

    public String getReceiverMobileNumber() {
        return receiverMobileNumber;
    }

    public void setReceiverMobileNumber(String receiverMobileNumber) {
        this.receiverMobileNumber = receiverMobileNumber;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Timestamp getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Timestamp estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Timestamp deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
