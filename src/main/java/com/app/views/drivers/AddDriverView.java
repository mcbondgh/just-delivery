package com.app.views.drivers;

import com.app.custom.ComponentLoader;
import com.app.custom.CustomComponents;
import com.app.layouts.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.io.IOException;
import java.util.Base64;

@PageTitle("Add Driver")
@Route(value = "views/add-driver", layout = MainLayout.class)
@RolesAllowed({"ADMIN", "STAFF"})
public class AddDriverView extends VerticalLayout {
    private final TextField nameField = new TextField("Driver's Name");
    private final TextField numberField = new TextField("Mobile Number");
    private final EmailField emailField = new EmailField("Driver's Email");
    private final TextField digitalAddField = new TextField("Digital Address");
    private final ComboBox<String> licenseTypeSelector = new ComboBox<>("License Type");
    private final TextField nationalIdNumber = new TextField("National Id Number");
    private final Upload uploadPhoto = new Upload();
    private final ComboBox<String> dispatchTypeSelector = new ComboBox<>("Means Of Dispatch");
    private final TextArea additionalInfo = new TextArea("Additional Information");
    private final Button addButton = new Button("Add Driver");
    private final Button backButton = new Button("Back");

    private static final int UPLOAD_FLE_SIZE = 5 * (1024 * 1024);

    public AddDriverView() {
        addClassNames("page-view", "add-driver-layout");
        setSizeFull();
        setBoxSizing(BoxSizing.BORDER_BOX);
        setSpacing(true);
        add(createPageHeader(), createPageLayout());
    }

    HorizontalLayout createPageHeader() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassNames("profile-header-container", "add-driver-header-container");
        layout.setWidthFull();
        H5 text = new H5("ADD A NEW DRIVER TO LIST");
        text.addClassNames("profile-header-text");
        backButton.addClassNames("tracker-button", "back-button");
        backButton.addThemeVariants(ButtonVariant.LUMO_SMALL);

        //add action event to back button to reroute to the addDriver Page.
        backButton.addClickListener(listener -> UI.getCurrent().navigate(DriversView.class));

        layout.add(text, backButton);
        return layout;
    }//end of method

    Component createPageLayout() {
        FlexLayout layout = new FlexLayout();
        layout.addClassNames("image-and-form-container");
        layout.add(profileImageSection(), formSection());
        return layout;
    }//end of method

    Component profileImageSection() {
        Div imageDiv = new Div();
        imageDiv.addClassNames("container", "driver-image-container");

        H6 imageHeader = new H6("UPLOAD YOUR IMAGE");
        Span imageText = new Span("Image size should not exceed 5MB");
        Div textContainer = new Div(imageHeader, imageText);
        textContainer.addClassNames("driver-text-container");

        //UPLOAD IMAGE
        MemoryBuffer imageBuffer = new MemoryBuffer();
        Button uploadButton = new Button("Upload Image");
        uploadPhoto.addClassName("upload-photo-frame");
        uploadPhoto.setReceiver(imageBuffer);
        uploadPhoto.setAcceptedFileTypes(".png", ".jpg");
        uploadPhoto.setMaxFiles(1);

        uploadPhoto.setMaxFileSize(UPLOAD_FLE_SIZE);
        uploadButton.addClassNames("upload-button");
        uploadPhoto.setUploadButton(uploadButton);
        uploadButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_SUCCESS);

        Image image = new Image("images/user-100.png", "img");
        image.addClassNames("driver-image");

        imageDiv.add(textContainer, image, uploadPhoto);

        //get  The image and show
        uploadPhoto.addSucceededListener(event -> {
                String fileName = event.getFileName();
                uploadPhoto.getDropLabel().getStyle().setFontSize("10px");
            try {
                byte[] imgBytes = imageBuffer.getInputStream().readAllBytes();
                String file = Base64.getEncoder().encodeToString(imgBytes);
                image.setSrc("data:image/png;base64," + file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return imageDiv;
    }//end of method


    FormLayout formSection() {
        FormLayout formLayout = new FormLayout();
        H6 headerText = new H6("Driver's Information");
        formLayout.addClassNames("container", "add-driver-form");
        formLayout.setSizeFull();

        ComponentLoader.setDispatcherType(dispatchTypeSelector);
        ComponentLoader.setLicenseTypes(licenseTypeSelector);
        Div buttonContainer = new Div(addButton);
        buttonContainer.addClassNames("add-driver-button-container");
        addButton.addClassNames("tracker-button", "add-driver-button");
        buttonContainer.setWidthFull();

        formLayout.add(
                headerText, nameField, numberField, emailField, digitalAddField,
                licenseTypeSelector, dispatchTypeSelector,
                nationalIdNumber, additionalInfo, buttonContainer);
        formLayout.getChildren().forEach(item -> {
            item.addClassNames("item-label");
        });
        CustomComponents.miniFieldsLayout(formLayout);
        CustomComponents.setComponentSpan(formLayout, 3, headerText);
        CustomComponents.setComponentSpan(formLayout, 3, nameField);
        CustomComponents.setComponentSpan(formLayout, 3, additionalInfo);
        CustomComponents.setComponentSpan(formLayout, 3, buttonContainer);

        return formLayout;
    }//end of method



    //ACTION EVENTS IMPLEMENTATION
}
