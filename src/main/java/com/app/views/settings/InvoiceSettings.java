package com.app.views.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import com.app.layouts.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileData;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.StreamResource;

@PageTitle("Invoice Setting")
@Route(value = "settings/invoice-settings", layout = MainLayout.class)
public class InvoiceSettings extends VerticalLayout {

    TextField companyNameField = new TextField("Company Name");
    TextField contactField = new TextField("Company Contact Number");
    EmailField emailField = new EmailField("Company Email");
    TextField addressField = new TextField("Digital Address");
    TextField deliveryPriceField = new TextField("Price Per Kilometer");
    MultiFileMemoryBuffer receiver = new MultiFileMemoryBuffer();
    Upload uploadLogo = new Upload(receiver);
    
    public InvoiceSettings() {
        setClassName("page-view");
        setSizeFull();
        setSpacing(true);
        add(pageHeaderSection(), pageFormSection());
        setComponentClassNames();
    }

    public void setComponentClassNames() {
        companyNameField.setClassName("form-text-field");
        contactField.setClassName("form-text-field");
        emailField.setClassName("form-text-field");
        addressField.setClassName("form-text-field");
        deliveryPriceField.addClassNames("form-text-field");
        deliveryPriceField.setPrefixComponent(new Div("Ghc "));
        uploadLogo.setClassName("form-text-field");
        uploadLogo.setMaxFileSize(2000000); // 2MB limit
    }

    private Component pageHeaderSection() {
        H5 headerText = new H5("INVOICE SETTINGS");
        Button saveButton = new Button("Save");
        saveButton.addClassNames("tracker-button", "save-invoice-settings-button");
        HorizontalLayout layout = new HorizontalLayout(headerText, saveButton);
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        layout.addClassName("invoice-header-layout");

        return layout;
    }

    private Component pageFormSection() {
        VerticalLayout layout = new VerticalLayout();
        layout.addClassNames("invoice-settings-layout");
        layout.setWidthFull();

        Image logoImage = new Image();
        logoImage.setAlt("Logo");
        logoImage.setClassName("invoice-logo");
        uploadLogo.setAcceptedFileTypes(".png,.jpg,.jpeg");

        Div logoContainer = new Div(logoImage, uploadLogo);
        logoContainer.addClassName("logo-container");
        
        uploadLogo.addSucceededListener(event -> {
            String fileName = event.getFileName();
            byte[] fileContent = null;
            try {
                fileContent = receiver.getInputStream(fileName).readAllBytes();
                String imageValue = Base64.getEncoder().encodeToString(fileContent);
                logoImage.setSrc("data:image/png;base64,"+ imageValue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        layout.add(companyNameField, contactField, emailField, addressField, deliveryPriceField, logoContainer);
        return layout;
    }



}