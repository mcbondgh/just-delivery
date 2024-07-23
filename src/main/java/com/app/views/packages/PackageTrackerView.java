package com.app.views.packages;

import com.app.custom.ComponentLoader;
import com.app.custom.CustomComponents;
import com.app.layouts.HomeLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Package Tracker")
@Route(value = "packages/tracking", layout = HomeLayout.class)
public class PackageTrackerView extends VerticalLayout {

    private final Button trackButton = new Button("Track");
    private final TextField searchField = new TextField("Enter Tracking Number");

    TextField senderName = new TextField("Sender's Name");
    EmailField senderEmail = new EmailField("Your Email");
    TextField senderNumberField = new TextField("Your Mobile Number");
    ComboBox<String> regionSelector = new ComboBox<>("Select Region");
    ComboBox<String> packageType = new ComboBox<>("Package Type");
    TextArea itemDesField = new TextArea("Describe Package Here.");
    TextField pickupAddressField = new TextField("Pickup Address");
    NumberField weightField = new NumberField("Package Weight");
    TextField priceField = new TextField("TOTAL PRICE");
    H5 headerText;
    Button placeOrderButton = new Button("PLACE ORDER");

    //RECEIVER FIELDS
    TextField receiverNameField = new TextField("Receiver's Name");
    TextField receiverNumberField = new TextField("Receiver's Mobile Number");
    EmailField receiverMailField = new EmailField("Receiver's Email(Optional)");
    TextField receiverAddressField = new TextField("Receiver's Address");

    PackageTrackerView() {
        addClassNames("package-page-layout");
        add(
                pageTitleContainer(),
                renderContentLayout()
        );
        componentClassNames();
        actionEventListenersImplementation();
    }


    /***************************************************************************************************************
                                             SET COMPONENT CLASS NAMES
     **************************************************************************************************************/
    void componentClassNames() {
        trackButton.addClassNames("track-button");
        searchField.addClassNames("search-field");
        senderName.addClassNames("item-label");
        senderEmail.addClassNames("item-label");
        senderNumberField.addClassNames("item-label");
        packageType.addClassNames("item-label");
        receiverAddressField.addClassNames("item-label");
        itemDesField.addClassNames("item-label");
        priceField.addClassNames("item-label", "package-price-field");
        regionSelector.addClassNames("item-label");
        weightField.addClassNames("item-label");
        pickupAddressField.addClassNames("item-label");
        receiverNameField.addClassNames("item-label");
        receiverNumberField.addClassNames("item-label");
        receiverMailField.addClassNames("item-label");

    }

    FormLayout renderContentLayout() {
        FormLayout layout = new FormLayout(packageSenderFormSection(), packageReceiverFormSection());
        layout.setWidthFull();
        layout.addClassNames("package-page-form-layout");
        return layout;
    }

    /***************************************************************************************************************
                                             PACKAGE TRACKER VIEW RENDERER
     **************************************************************************************************************/
    Section pageTitleContainer() {
        Section container = new Section();
        container.addClassNames("package-page-header-container");
        Span headerText = new Span("TRACK OR SEND A PACKAGE HERE");
        headerText.addClassNames("package-page-hero-text");

        container.add(headerText, packageTrackerButtonSection());
        container.getStyle().setJustifyContent(Style.JustifyContent.SPACE_BETWEEN);
        return container;
    }

    /***************************************************************************************************************
     PACKAGE TRACKER BUTTON CONTAINER
     **************************************************************************************************************/

    Div packageTrackerButtonSection() {
        Button trackItemButton = new Button("Track Package");

        Div buttonContainer = new Div(trackItemButton);
        buttonContainer.addClassNames("container");
        trackItemButton.addClassNames("track-button", "show-tracker-dialog");

        trackItemButton.addClickListener(click -> {
            packageDisplayDialog().open();
        });
        return buttonContainer;
    }

    Dialog packageDisplayDialog() {
        H5 dialogHeader = new H5("PACKAGE INFORMATION");
        FlexLayout layout = new FlexLayout(dialogHeader);
        VerticalLayout verticalLayout = new VerticalLayout(searchField, trackButton);

        //CREATE A DIV CONTAINER TO HOLD ITEM STATUS;
        Span placedStatus = new Span("Order Placed");
        Span assignedStatus = new Span("Driver Assigned");
        Span transitionStatus = new Span("Dispatched");
        Span deliveredStatus = new Span("Delivered");

        //set images
        Image placedImg = new Image("icons/icons8-weight-50.png", "img");
        Image assignedImg = new Image("icons/icons8-delivery-64.png", "img");
        Image transImg = new Image("icons/icons8-motion-48.png", "img");
        Image deliveredImg = new Image("icons/data-quality.png", "img");

        //setClassNames
        placedStatus.addClassNames("item-status", "placed-status");
        assignedStatus.addClassNames("item-status", "assigned-status");
        transitionStatus.addClassNames("item-status", "transition-status");
        deliveredStatus.addClassNames("item-status", "delivered-status");

        Div placedContainer = new Div(placedImg, placedStatus);
        Div assignedContainer = new Div(assignedImg, assignedStatus);
        Div transContainer = new Div(transImg, transitionStatus);
        Div deliveredContainer = new Div(deliveredImg, deliveredStatus);

        placedContainer.addClassNames("item-container");
        assignedContainer.addClassNames("item-container");
        transContainer.addClassNames("item-container");
        deliveredContainer.addClassNames("item-container");

        placedContainer.getElement().setAttribute("status", "true");
        assignedContainer.getElement().setAttribute("status", "false");
        transContainer.getElement().setAttribute("status", "false");
        deliveredContainer.getElement().setAttribute("status", "false");

        FormLayout contentContainer = new FormLayout(placedContainer, assignedContainer, transContainer, deliveredContainer);
        //check if the status of the item is set to TRUE, if TRUE enable container else disable container


        contentContainer.getChildren().forEach(item -> {
            boolean value = Boolean.parseBoolean(item.getElement().getAttribute("status"));
            if (value) {
                item.removeClassName("item-disable");
            } else item.addClassNames("item-disable");
        });

        Dialog dialog = new Dialog(verticalLayout, contentContainer);
        dialog.getHeader().add(layout);
        dialog.setResizable(true);
        dialog.setDraggable(true);
        dialog.setCloseOnOutsideClick(true);
        dialog.setWidth("650px");
        dialog.setHeightFull();

        verticalLayout.addClassNames("dialog-vertical-layout");
        dialogHeader.addClassNames("dialog-header-title");
        layout.addClassNames("package-tracker-section");
        dialog.addClassNames("package-tracker-dialog");
        contentContainer.addClassNames("dialog-content-container");

        return dialog;
    }

    /***************************************************************************************************************
     CREATE AND RENDER PACKAGE SENDER FORM
     **************************************************************************************************************/
    VerticalLayout packageSenderFormSection() {
        VerticalLayout layout = new VerticalLayout();
        headerText = new H5("SENDER INFORMATION");

        layout.addClassNames("sender-form-layout");
        headerText.addClassNames("sender-header-text");

        weightField.setStep(1);
        weightField.setStepButtonsVisible(true);
        weightField.setSuffixComponent(new H6("KG"));

        Image phoneIcon = new Image("icons/phone-64.png", "img");
        phoneIcon.addClassNames("icons");
        senderNumberField.setPrefixComponent(new Span("+233"));
        Image regionIcon =new Image("icons/globe.png", "img");
        regionIcon.addClassNames("icons");
        regionSelector.setPrefixComponent(regionIcon);
        Image locationAddress = new Image("icons/location.png", "img");
        locationAddress.addClassNames("icons");
        pickupAddressField.setPrefixComponent(locationAddress);
        receiverAddressField.setPrefixComponent(locationAddress);

        //LOAD COMBOBOX WITH DATA
        ComponentLoader.setPackageType(packageType);
        ComponentLoader.setRegionNames(regionSelector);

        FormLayout senderContainer = new FormLayout(senderName, senderEmail, senderNumberField, packageType, regionSelector, weightField, pickupAddressField, itemDesField);

        //SET RESPONSIVE FORM-LAYOUT TO ADJUST ITEMS
        CustomComponents.defaultFormColumns(senderContainer);
        CustomComponents.setComponentSpan(senderContainer, 2, pickupAddressField);
        CustomComponents.setComponentSpan(senderContainer, 2, itemDesField);

        layout.add(headerText, senderContainer);
        return layout;
    }

    /***************************************************************************************************************
     CREATE AND RENDER PACKAGE RECEIVER FORM
     **************************************************************************************************************/
    VerticalLayout packageReceiverFormSection() {
        VerticalLayout layout = new VerticalLayout();
        headerText = new H5("RECEIVER INFORMATION");
        headerText.addClassNames("sender-header-text");
        priceField.setReadOnly(true);
        priceField.setPrefixComponent(new Span("Ghc "));

        H5 priceHeaderText = new H5("PACKAGE PRICE");
        priceHeaderText.addClassNames("sender-header-text");
        Div priceContainer = new Div(priceHeaderText, priceField);
        priceContainer.addClassNames("sender-header-text");

        placeOrderButton.addClassNames("tracker-button", "place-order-button");

        FormLayout formLayout = new FormLayout(receiverNameField, receiverNumberField, receiverAddressField, receiverMailField, priceContainer, placeOrderButton);
       CustomComponents.defaultFormColumns(formLayout);
        CustomComponents.setComponentSpan(formLayout, 2, priceContainer);
        CustomComponents.setComponentSpan(formLayout, 2, placeOrderButton);
        layout.add(headerText, formLayout);
        return layout;
    }


    /***************************************************************************************************************
     ACTION EVENT FOR TRACKER BUTTON CLICKED.
     **************************************************************************************************************/
    private void actionEventListenersImplementation() {

        //calculate price based on item weight.
        // 1kg = Ghc 2.50
        double initialPrice = 2.5;
        weightField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        weightField.setValueChangeMode(ValueChangeMode.EAGER);
        weightField.addValueChangeListener(event -> {
            try{
                double price = weightField.getValue() * initialPrice;
                priceField.setValue(String.valueOf(price));
            }catch (NullPointerException ignore){priceField.setValue("0.00");}
        });


    }

}//END OF CLASS...
