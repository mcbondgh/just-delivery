package com.app.views.customers;

import com.app.custom.CustomComponents;
import com.app.layouts.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Your Profile")
@Route(value = "views/customer-profile", layout = MainLayout.class)
@RolesAllowed({"CUSTOMER", "BUSINESS", "ADMIN"})
public class CustomerProfileView extends VerticalLayout {

    public CustomerProfileView() {
        setClassName("page-view");
        setPadding(true);
        setSizeFull();
        add(pageInnerContainer());
    }


    private TextField nameField = new TextField("Customer Name");
    private TextField numberField = new TextField("Mobile Number");
    private EmailField emailField = new EmailField("Email");
    private TextField addressField = new TextField("Address");
    private TextArea descriptionArea = new TextArea("Additional Info");
    private TextField dateField = new TextField("Date Registered");
    private PasswordField password1 = new PasswordField("Password");
    private PasswordField confirmPwd = new PasswordField("Confirm Password");
    private Button updateButton = new Button("Update Data");
    private Button deleteButton = new Button("Delete Account");
    private Checkbox checkbox = new Checkbox("Update Password");

    VerticalLayout pageInnerContainer() {
        VerticalLayout layout = new VerticalLayout(createPageHeaderSection(), createCustomerFormSection());
        layout.setClassName("profile-inner-container");
        return layout;
    }



    HorizontalLayout createPageHeaderSection() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.setClassName("profile-header-container");

        H5 headerText = new H5("HELLO, CUSTOMER NAME!");
        headerText.addClassNames("profile-header-text");
        layout.add(headerText);
        return layout;
    }//end of method


    Component createCustomerFormSection() {
        FormLayout layout = new FormLayout();
        CustomComponents.defaultFormColumns(layout);
        layout.addClassNames("profile-form-layout");

        //set classNames
        nameField.addClassNames("item-label");
        numberField.addClassNames("item-label");
        emailField.addClassNames("item-label");
        addressField.addClassNames("item-label");
        descriptionArea.addClassNames("item-label");
        dateField.addClassNames("item-label");
        password1.addClassName("item-label");
        confirmPwd.addClassName("item-label");
        updateButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        updateButton.addClassNames("tracker-button", "profile-update-button");
        deleteButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR);
        checkbox.addClassNames("check-box");
        checkbox.setTooltipText("Select to enable password update").setPosition(Tooltip.TooltipPosition.BOTTOM_START);

        Div pwdContainer = new Div(password1, confirmPwd);
        pwdContainer.addClassNames("profile-pwd-container");
        Div itemContainer = new Div(checkbox, pwdContainer);
        itemContainer.addClassNames("profile-item-container");
        Div buttonsContainer = new Div(updateButton, deleteButton);
        buttonsContainer.addClassNames("profile-buttonsContainer");

        //ADD EVENT HANDLER TO CHECKBOX
        checkbox.setValue(false);
        pwdContainer.setEnabled(false);
        checkbox.addValueChangeListener(event -> {
            pwdContainer.setEnabled(checkbox.getValue());
        });

//        CustomComponents.setComponentSpan(layout, 2, nameField);
//        CustomComponents.setComponentSpan(layout, 2, itemContainer);
//        CustomComponents.setComponentSpan(layout, 2, addressField);
        CustomComponents.setComponentSpan(layout, 2, buttonsContainer);

        layout.add(nameField, numberField, addressField, emailField, itemContainer, descriptionArea, buttonsContainer);
        return layout;
    }



}//end of class...
