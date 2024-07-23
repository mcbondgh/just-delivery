package com.app.views.staff;

import com.app.custom.ComponentLoader;
import com.app.data.StaffData;
import com.app.layouts.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Add Staff")
@RolesAllowed({"ADMIN", "STAFF"})
@Route(value = "views/add-staff", layout = MainLayout.class)
public class AddStaffMemberView extends VerticalLayout implements BeforeEnterObserver {

    public AddStaffMemberView() {
        setSizeFull();
        setSpacing(true);
        setClassName("page-view");
        add(
                pageContainer()
        );
        implementationOfActionEventMethods();
        setInputFieldClassNames();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

    }


    private final Button addStaffButton = new Button("Add Staff");
    private final Grid<StaffData> staffTable = new Grid<>(StaffData.class, false);
    private final TextField nameField = new TextField("Full Name");
    private final TextField mobileNumberField = new TextField("Mobile Number");
    private final EmailField emailField = new EmailField("Email");
    private final TextArea descriptionField = new TextArea("Additional Info");
    private final PasswordField passwordField = new PasswordField("Password");
    private final PasswordField confirmPassword = new PasswordField("Confirm Password");
    private final Button submitButton = new Button("Submit");
    private final Button cancelButton = new Button("Cancel");
    private final ComboBox<String> genderSelector = new ComboBox<>("Gender");



    //SET CLASS NAMES for form fields
    void setInputFieldClassNames() {
        nameField.addClassNames("item-label");
        mobileNumberField.addClassNames("item-label");
        emailField.addClassNames("item-label");
        descriptionField.addClassNames("item-label");
        passwordField.addClassNames("item-label");
        confirmPassword.addClassNames("item-label");
        genderSelector.addClassNames("item-label");
        ComponentLoader.setGender(genderSelector);
    }

    /*******************************************************************************************************************
     PAGE VIEW IMPLEMENTATION
     ******************************************************************************************************************/

    VerticalLayout pageContainer() {
        VerticalLayout layout = new VerticalLayout(createHeaderSection(), createTableSection());
        layout.setSizeFull();
        layout.setClassName("staff-inner-page-container");
        return layout;
    }

    HorizontalLayout createHeaderSection() {
        HorizontalLayout layout = new HorizontalLayout();
        H6 headerText = new H6("STAFF MEMBERS LIST");

        layout.addClassNames("profile-header-container", "staff-header-container");
        layout.setWidthFull();
        headerText.addClassNames("staff-header-text");
        addStaffButton.addClassNames("tracker-button", "add-staff-button");


        layout.add(headerText, addStaffButton);
        return layout;
    }

    VerticalLayout createTableSection() {
        VerticalLayout layout = new VerticalLayout();
        layout.addClassNames("staff-table-section");
        layout.setSizeFull();
        staffTable.addClassNames("staff-data-grid");


        //CONFIGURE GRID COLUMNS
        staffTable.addColumn(StaffData::getId).setHeader("STAFF ID").setSortable(true).setFlexGrow(1);
        staffTable.addColumn(StaffData::getFullName).setHeader("FULL NAME");
        staffTable.addColumn(StaffData::getMobileNumber).setHeader("MOBILE NUMBER");
        staffTable.addColumn(StaffData::getEmail).setHeader("EMAIL");
        staffTable.addColumn(StaffData::isStatus).setHeader("STATUS");
        staffTable.addColumn(StaffData::getActionButton).setHeader("ACTION");

        staffTable.getColumns().forEach(item -> item.setAutoWidth(true));
        staffTable.setSizeFull();

        layout.add(staffTable);
        return layout;
    }

    /*******************************************************************************************************************
     * DIALOG FORM FOR ADDING NEW STAFF MEMBER.
     *******************************************************************************************************************/

    private Dialog formDialog() {
        Dialog dialog = new Dialog();
        dialog.addClassNames("staff-dialog-box");
        H5 dialogHeaderText = new H5("ADD NEW STAFF MEMBER");
        dialogHeaderText.addClassNames("add-staff-dialog-header-text");
        dialog.setWidth("800px");
        Span closeIcon = new Span(VaadinIcon.CLOSE.create());
        closeIcon.addClassNames("staff-dialog-close-button");

        //ADD DIALOG CLOSE ACTION TO THE ICON
        closeIcon.addClickListener(event -> {
            dialog.close();
        });

        FlexLayout headerLayout = new FlexLayout(dialogHeaderText, closeIcon);
        headerLayout.addClassNames("staff-dialog-header-container");

        FormLayout formlayout = new FormLayout();
        formlayout.addClassNames("staff-form-layout");
               
        Div buttonContainer = new Div(submitButton, cancelButton);
        submitButton.addClassNames("tracker-button", "staff-submit-button");
        cancelButton.addClassNames("cancel-button");
        buttonContainer.addClassNames("staff-menu-bar");
        cancelButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR);
        submitButton.addThemeVariants(ButtonVariant.LUMO_SMALL);

        formlayout.add(
                nameField, mobileNumberField, emailField, genderSelector, passwordField, confirmPassword, descriptionField
        );

        //set component column span
        formlayout.setColspan(descriptionField, 2);

        dialog.getFooter().add(buttonContainer);

        dialog.setCloseOnOutsideClick(false);
        dialog.setDraggable(true);
        dialog.getHeader().add(headerLayout);
        dialog.add(formlayout);
        return dialog;
    }

/*******************************************************************************************************************
 * IMPLEMENTATION OF ACTION EVENT METHODS
 *******************************************************************************************************************/
    void implementationOfActionEventMethods() {
        //action event for ADD STAFF button
        addStaffButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        addStaffButton.addClickListener(event -> {
            formDialog().open();
        });
    }

}//end of class...
