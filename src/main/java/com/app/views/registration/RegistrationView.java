package com.app.views.registration;

import com.app.custom.ComponentLoader;
import com.app.enumerators.RegistrationTypes;
import com.app.layouts.HomeLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PageTitle("User And Business Registration")
@Route(value = "/views/registration", layout = HomeLayout.class)
@RouteAlias(value = "/customer-registration", layout = HomeLayout.class)
@Uses(Icon.class)
public class RegistrationView extends VerticalLayout {

    private static final Logger log = LoggerFactory.getLogger(RegistrationView.class);

    public RegistrationView() {
        addClassName("homepage-layout");
        setSizeFull();
        add(
                renderPageHeroContainer(),
                renderPageRegistrationSection()
        );
    }

    HorizontalLayout renderPageHeroContainer() {
        Span headerTitle = new Span("CUSTOMER REGISTRATION");
        HorizontalLayout layout = new HorizontalLayout(headerTitle);
        headerTitle.addClassNames("header-title-text");

        layout.addClassName("header-layout");
        return layout;
    }

    Component renderPageRegistrationSection() {
        FlexLayout layout = new FlexLayout();
        layout.addClassNames("registration-flex-layout");
        layout.setWidthFull();

        TextField firstNameField = new TextField("First Name");
        TextField lastNameField = new TextField("Last Name");
        TextField numberField = new TextField("Mobile Number");
        EmailField emailField = new EmailField("Email");
        PasswordField passwordField = new PasswordField("Enter Password");
        PasswordField confirmPassword = new PasswordField("Confirm Password");
        Button registerButton = new Button("Register");
        ComboBox<RegistrationTypes> registrationType = new ComboBox<>("Registration Type");
        registerButton.addClassNames("tracker-button");
        ComponentLoader.setRegistrationType(registrationType);

        Image logoImage = new Image("icons/main-logo.png", "JUST-DELIVERY");
        logoImage.addClassNames("logo-image");

        //set component class names
        firstNameField.addClassNames("item-label");
        lastNameField.addClassNames("item-label");
        numberField.addClassNames("item-label");
        emailField.addClassNames("item-label");
        passwordField.addClassName("item-label");
        confirmPassword.addClassName("item-label");
        registrationType.addClassNames("item-label");

        Section formSection = new Section(registrationType, firstNameField,
                lastNameField, numberField, emailField, passwordField,
                confirmPassword, registerButton);
        Section logoSection = new Section(logoImage);
        formSection.setWidthFull();
        logoSection.setWidthFull();
        formSection.addClassNames("section-layout", "form-section");
        logoSection.addClassNames("section-layout", "logo-section");

        layout.add(formSection, logoSection);
        return layout;
    }






}//end of class...
