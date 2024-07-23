package com.app.views.invoice;


import com.app.layouts.MainLayout;
import com.app.views.orders.OrdersView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hibernate.query.Order;

@PageTitle("Order Invoice")
@Route(value = "order-details/invoice", layout = MainLayout.class)
public class OrderInvoicesView extends VerticalLayout {

    private final Button generateInvoiceButton = new Button("Generate Invoice ");
    private final Button backButton = new Button("< Back");
    private final TextField filterField = new TextField("Enter Invoice Number");

    public OrderInvoicesView() {
        addClassName("page-view");
        setSizeFull();
        setBoxSizing(BoxSizing.BORDER_BOX);
        setComponentClassNames();
        backButtonClicked();
        generateInvoiceButtonButtonClicked();
        add(headerWithButtonsLayout(), orderDetailsPageLayout());

    }


    private void setComponentClassNames() {
        generateInvoiceButton.addClassNames("tracker-button", "generate-invoice-button");
        generateInvoiceButton.setSuffixComponent(VaadinIcon.DOWNLOAD.create());
        backButton.addClassNames("tracker-button", "invoice-back-button");
    }

    private HorizontalLayout headerWithButtonsLayout() {
        H5 headerText = new H5("Order Details");
        headerText.addClassNames("header-text", "invoice-header-text");

        Div buttonContainer = new Div(generateInvoiceButton, backButton);
        buttonContainer.addClassNames("button-container");

        HorizontalLayout layout = new HorizontalLayout(headerText, buttonContainer);
        layout.addClassNames("invoice-header-layout");

        return layout;
    }


    /*******************************************************************************************************************
                DESIGN PARCEL LAYOUT
     ******************************************************************************************************************/
    private Component createParcelDetailsBody() {
        H6 parcelTypeLabel = new H6("Parcel Type");
        Span parcelTypeValue = new Span("Unspecified");
        Div parcelTypeContainer = new Div(parcelTypeLabel, parcelTypeValue);
        parcelTypeContainer.addClassNames("parcel-inner-div");

        H6 parcelWeightLabel = new H6("Weight");
        Span parcelWeightValue = new Span("Unspecified");
        Div weightContainer = new Div(parcelWeightLabel, parcelWeightValue);
        weightContainer.addClassNames("parcel-inner-div");

        H6 quantityLabel = new H6("Quantity");
        Span quantityValue = new Span("10");
        Div quantityContainer = new Div(quantityLabel, quantityValue);
        quantityContainer.addClassNames("parcel-inner-div");

        H6 distanceLabel = new H6("Distance");
        Span distanceValue = new Span("83.km");
        Div distanceContainer = new Div(distanceLabel, distanceValue);
        distanceContainer.addClassNames("parcel-inner-div");

        H6 paymentLabel = new H6("Payment Method");
        Span paymentValue = new Span("Cash");
        Div variablesContainer1 = new Div(paymentLabel, paymentValue);
        variablesContainer1.addClassNames("parcel-inner-div");

        H6 paymentStatusLabel = new H6("Status");
        Span paymentStatusValue = new Span("Pending");
        Div statusContainer = new Div(paymentStatusLabel, paymentStatusValue);
        statusContainer.addClassNames("parcel-inner-div");

        H6 paymentTypeLabel = new H6("Payment Type" );
        Span paymentTypeValue = new Span("On Delivery");
        Div paymentTypeContainer = new Div(paymentTypeLabel, paymentTypeValue);
        paymentTypeContainer.addClassNames("parcel-inner-div");

        Div paymentContainer = new Div(variablesContainer1);
        paymentContainer.addClassNames("parcel-inner-div");

        H5 costLabel = new H5("TOTAL DELIVERY COST");
        H4 costValue = new H4("Ghc 2000.00");
        Div costContainer = new Div(costLabel, costValue);
        costContainer.addClassNames("parcel-inner-div", "delivery-cost-container");

        H6 orderNumberLabel = new H6("ORDER NUMBER");
        Span orderNumValue = new Span("#009493493-294");
        Div orderContainer = new Div(orderNumberLabel, orderNumValue);
        orderContainer.addClassNames("parcel-inner-div", "order-container");

        Div packageContainerDiv = new Div(orderContainer, parcelTypeContainer, 
                                            weightContainer, quantityContainer, distanceContainer, 
                                        paymentTypeContainer, statusContainer, costContainer);
        packageContainerDiv.addClassName("package-container");


        //create a section for parcel details
        Section parcelDetailsContainer = new Section(packageContainerDiv);
        parcelDetailsContainer.addClassNames("parcel-info-section", "parcel-details-section");
        return parcelDetailsContainer;
    }

    //********************************************* */
    private Component customerInfoSection() {
       
        // H5 deliveryHeader = new H5("Address Details");

        H6 pickupLabel = new H6("Pickup Address");
        Span pickupAddress = new Span("Unspecified");
        Div pickupAddressContainer = new Div(pickupLabel, pickupAddress);
        pickupAddressContainer.addClassNames("parcel-inner-div");

        H6 pickupNumberLabel = new H6("Mobile Number");
        Span pickupNumberValue =  new Span("+233 0302300230");
        Div pickupNumberContainer = new Div(pickupNumberLabel, pickupNumberValue);
        pickupNumberContainer.addClassName("parcel-inner-div");

        H6 destinationLabel = new H6("Destination Address");
        Span destinationAddress = new Span("Unspecified");
        Div destinationAddressContainer = new Div(destinationLabel, destinationAddress);
        destinationAddressContainer.addClassNames("parcel-inner-div");

        H6 destinationNumberLabel = new H6("Mobile Number");
        Span destinationNumberValue =  new Span("+233 0302300230");
        Div destinationNumberContainer = new Div(destinationNumberLabel, destinationNumberValue);
        destinationNumberContainer.addClassName("parcel-inner-div");

        H6 senderNameLabel = new H6("Sender Name");
        Span senderNameValue = new Span("Sender's Fullname");
        Div senderContainer = new Div(senderNameLabel, senderNameValue);
        senderContainer.addClassName("parcel-inner-div");

        H6 receiverLabel = new H6("Receiver's Name");
        Span receiverValue = new Span("Receiver's Fullname");
        Div receiverContainer = new Div(receiverLabel, receiverValue);
        receiverContainer.addClassName("parcel-inner-div");

        Section layout = new Section(senderContainer, receiverContainer, pickupAddressContainer, pickupAddressContainer, pickupNumberContainer, destinationAddressContainer, destinationNumberContainer);
        layout.addClassNames("parcel-info-section", "customer-info-section");
        return layout;
    }

    private Component orderDetailsPageLayout() {
        FormLayout layout = new FormLayout();
        layout.addClassNames("order-details-body");

        H5 header = new H5("Parcel Information");
        header.addClassNames("content-header");
        Section parcelDetailsSection = new Section(header, createParcelDetailsBody());

        H5 header2 = new H5("Customer Details");
        header2.addClassNames("content-header");
        Section customerDetails = new Section(header2, customerInfoSection());

        layout.add(parcelDetailsSection, customerDetails);

        return layout;
    }

    /*******************************************************************************************************************
            ACTION EVENT METHODS IMPLEMENTATION.
     *******************************************************************************************************************/
    void backButtonClicked() {
        backButton.addClickListener(event ->UI.getCurrent().navigate(OrdersView.class));
    }

    private void generateInvoiceButtonButtonClicked() {
        generateInvoiceButton.addClickListener(event -> {

        });
    }



}//end of class...
