package com.app.custom;


import com.app.api.ApiCalls;
import com.app.enumerators.RegistrationTypes;
import com.vaadin.flow.component.combobox.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class ComponentLoader {

    static ApiCalls API_CALLS;
   public static void setPackageType(ComboBox<String> comboBox) {
       String[] items = {"Document", "Book", "Electronics", "Gadgets", "Food", "Clothing", "Shoes", "Parcel", "Flower", "Medical Item", "Other"};
        comboBox.setItems(items);
    }
    public static void setPaymentMethods(ComboBox<String> comboBox){
        String[] items = {"Delivery Point", "Pickup Point", "e-Payment"};
        comboBox.setItems(items);
    }

    public static void setLicenseTypes(ComboBox<String> comboBox) {
        String[] items = {"TYPE A", "TYPE B", "TYPE C", "TYPE D", "TYPE E"};
        comboBox.setItems(items);
    }

    public static void setDispatcherType(ComboBox<String> comboBox) {
        String[] items = {"Mini Van", "Motorbike", "Car"};
        comboBox.setItems(items);
    }

    public static void setGender(ComboBox<String> comboBox) {
        String[] items = {"Male", "Female", "Other"};
        comboBox.setItems(items);
    }

    public static void setRegionNames(ComboBox<String> comboBox) {
        API_CALLS = new ApiCalls();
        comboBox.setItems(API_CALLS.getRegionalNames());
    }
    public static void setRegistrationType(ComboBox<RegistrationTypes> comboBox) {
        List<RegistrationTypes> items = new ArrayList<>();
        items.add(RegistrationTypes.BUSINESS);
        items.add(RegistrationTypes.INDIVIDUAL);
        items.add(RegistrationTypes.RIDER);
        comboBox.setItems(items);
    }

    public static void setTableLimit(ComboBox<Integer> limitSelector) {
       Integer[] items = {1, 10, 20, 40, 80, 200, 500, 1000};
       limitSelector.setItems(items);
    }
}
