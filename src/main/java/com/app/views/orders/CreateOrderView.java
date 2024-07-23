package com.app.views.orders;

import com.app.api.MapApiCall;
import com.app.custom.ComponentLoader;
import com.app.layouts.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.ScrollerVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

@PageTitle("Create Order")
@Route(value = "views/create-order", layout = MainLayout.class)
@Uses(Icon.class)
public class CreateOrderView extends VerticalLayout {

    public CreateOrderView() {
        setSizeFull();
        addClassNames("page-view");
        setSpacing(true);
        declareComponentClassNames();
        openMapOnButtonClicked();

        VerticalLayout pageInnerLayout = new VerticalLayout(createPageHeaderLayout(), createPageFormLayout());
        pageInnerLayout.setClassName("create-order-parent-layout");
        calculateDeliveryPrice();

        add(pageInnerLayout);
    }

    private ComboBox<String> packageTypeSelector = new ComboBox<>("PackageType");
    private NumberField weightSetter = new NumberField("Package Weight(kg)");
    private NumberField packageCounter = new NumberField("Package Quantity");
    private TextArea packageDescInput = new TextArea("Describe Package Here");
    private EmailField senderMailInput = new EmailField("Sender's Email");
    private TextField pickupAddressInput = new TextField("Pickup Address");
    private EmailField receiverMailInput = new EmailField("Receiver's Email");
    private TextField senderNameInput = new TextField("Sender's Name");
    private TextField receiverNameInput = new TextField("Receiver's Name");
    private NumberField senderNumberInput = new NumberField("Sender's Mobile Number");
    private NumberField receiverNumberInput = new NumberField("Receiver's Number");
    private TextField destinationAddressInput = new TextField("Destination Address");
    private TextArea senderAdditionalInfoInput = new TextArea("Additional Information");
    private TextArea receiverAdditionalInfoInput = new TextArea("Additional Information");
    private NumberField distanceInput = new NumberField("Distance(km)");
    private Button saveOrderButton = new Button("Save Order");
    private ComboBox<String> paymentMethodSelector = new ComboBox<>("Payment Method");
    private ComboBox<String> delliveryTypeSelector = new ComboBox<>("Delivery Type");
    private Span costLabel = new Span("0.00");
    private final Button getLocationButton = new Button();

    /*******************************************************************************************************************
     *  COMPONENT STYLE, CLASS AND ID DECLARATION METHOD
     *******************************************************************************************************************/
    public void declareComponentClassNames() {
        packageTypeSelector.addClassNames("item-label", "package-type-selector");
        weightSetter.addClassNames("item-label", "weight-selector");
        packageCounter.addClassNames("item-label", "package-counter-selector");
        packageDescInput.addClassNames("item-label", "package-description");
        senderMailInput.addClassNames("item-label", "package-sender-input");
        receiverMailInput.addClassNames("item-label", "package-receiver-input");
        senderNameInput.addClassNames("item-label", "package-sender-name");
        receiverNameInput.addClassNames("item-label", "package-receiver-name");
        senderNumberInput.addClassNames("item-label", "package-sender-number");
        pickupAddressInput.addClassNames("item-label", "package-pickup-address");
        destinationAddressInput.addClassNames("item-label", "package-destination");
        pickupAddressInput.addClassNames("item-label", "package-pickup-address");
        receiverNumberInput.addClassNames("item-label", "package-receiver-number");
        pickupAddressInput.addClassNames("item-label", "additionalInfo");
        receiverAdditionalInfoInput.addClassNames("item-label", "additionalInfo");
        saveOrderButton.addClassNames("tracker-button", "save-order-button");
        paymentMethodSelector.addClassNames("item-label", "payment-method-selector");
        costLabel.setClassName("cost-label");

        saveOrderButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        getLocationButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        ComponentLoader.setPackageType(packageTypeSelector);
        ComponentLoader.setPaymentMethods(paymentMethodSelector);
        weightSetter.setStep(1);
        weightSetter.setValue(1.0);
        weightSetter.setStepButtonsVisible(true);
        packageCounter.setStep(1);
        packageCounter.setStepButtonsVisible(true);
        distanceInput.setValue(1.0);
    }

    /*******************************************************************************************************************
     *  CREATE PAGE SECTIONS
     *******************************************************************************************************************/
    public FlexLayout createPageHeaderLayout() {
        H5 headerText = new H5("CREATE NEW ORDER");
        FlexLayout layout = new FlexLayout(headerText, saveOrderButton);
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        headerText.setClassName("create-order-page-header-text");
        layout.addClassNames("create-order-page-header-container", "profile-header-container");
        layout.setWidthFull();
        return layout;
    }

    public Component createPageFormLayout() {
        FormLayout layout = new FormLayout();
        Scroller scroller = new Scroller(layout);
        scroller.addClassNames("order-form-scroller");
        layout.addClassNames("create-order-form-layout");

        VerticalLayout itemFlexLayout = packageCalculatorSection();

        layout.add(createPackageSenderSection(), createPackageReceiverSection(), itemFlexLayout);
        layout.setColspan(itemFlexLayout, 2);

        scroller.addThemeVariants(ScrollerVariant.LUMO_OVERFLOW_INDICATORS);
        return scroller;
    }

    private @NotNull VerticalLayout packageCalculatorSection() {
        Paragraph paragraph = new Paragraph("""
                Please note that the price of your delivery is calculated based on distance from and to destination\s
                plus(+) the weight of the package you wish to transport in Kg.
               \s""");
        paragraph.setClassName("notice-paragraph");

        Span priceLabel = new Span("Delivery Cost");
        priceLabel.setClassName("price-label");
        Div priceContainer = new Div(priceLabel, costLabel);
        priceContainer.addClassNames("price-container");

        getLocationButton.addClassNames("maplink");
        VerticalLayout itemFlexLayout = new VerticalLayout(
                paragraph,packageTypeSelector, weightSetter,
                packageCounter, distanceInput, priceContainer,
                paymentMethodSelector, getLocationButton
        );

        itemFlexLayout.addClassNames("create-order-selectors-layout");
        itemFlexLayout.setWidthFull();
        return itemFlexLayout;
    }

    private Component createPackageSenderSection() {
        Span senderHeaderText = new Span("Pickup Information (Sender)");
        Section senderSection = new Section();
        senderSection.setClassName("section-container");

        Div formContainer = new Div();
        formContainer.setClassName("sender-form-container");
        formContainer.add(senderNameInput, senderNumberInput, senderMailInput, pickupAddressInput, senderAdditionalInfoInput);

        senderSection.add(senderHeaderText, formContainer);
        return senderSection;
    }

    private Component createPackageReceiverSection() {
        Span receiverHeaderText = new Span("Destination Information (Receiver)");
        Section receiverSection = new Section(receiverHeaderText);
        receiverSection.setClassName("section-container");

        Div formContainer = new Div();
        formContainer.setClassName("sender-form-container");
        formContainer.add(receiverNameInput, receiverNumberInput, receiverMailInput, destinationAddressInput, receiverAdditionalInfoInput);

        receiverSection.add(formContainer);
        return receiverSection;
    }


    /*******************************************************************************************************************
     *  METHODS IMPLEMENTATION
     *******************************************************************************************************************/


    /*******************************************************************************************************************
     *  ACTION EVENT METHODS IMPLEMENTATION
     *******************************************************************************************************************/
    public void calculateDeliveryPrice() {
            DecimalFormat numberFormat = new DecimalFormat("0.00");
            double getCostPerKilometer = 0.8;// per each kilometer, we charge only 0.8
        try {
            weightSetter.setValueChangeMode(ValueChangeMode.EAGER);
            distanceInput.setValueChangeMode(ValueChangeMode.EAGER);

            weightSetter.addValueChangeListener(change -> {
                double distanceValue = distanceInput.getValue();
                if (change.getValue() == null) {
                    costLabel.setText("0.00");
                    return;
                }
                double result = ( distanceValue * getCostPerKilometer) * change.getValue();
                costLabel.setText(numberFormat.format(result));
            });
            distanceInput.addValueChangeListener(change -> {
                double weightValue = weightSetter.getValue();
                if (change.getValue()==null) {
                    costLabel.setText("0.00");
                    return;
                }
                double result = weightValue * (change.getValue() * getCostPerKilometer);
                costLabel.setText(numberFormat.format(result));
            });
        }catch (NullPointerException ignore) {
            costLabel.setText("0.00");
        }
    }

    void openMapOnButtonClicked() {
            Anchor anchor = new Anchor();
            anchor.setTarget("_blank");
            anchor.setText("Check Distance");
            getLocationButton.setPrefixComponent(anchor);
            getLocationButton.getElement().addEventListener("mouseover", e-> {
                String pickupAdd = pickupAddressInput.getValue();
                String destination = destinationAddressInput.getValue();
                String link = MapApiCall.openGoogleMap(pickupAdd, destination);
                anchor.setHref(link);
            });
    }




}//end of class...
