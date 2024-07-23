package com.app.views.drivers;

import com.app.data.DriverData;
import com.app.layouts.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.Objects;

@PageTitle("Drivers")
@Route(value = "views/drivers", layout = MainLayout.class)
@RolesAllowed({"ADMIN", "STAFF"})
public class DriversView extends VerticalLayout {

    public DriversView() {
        addClassName("page-view");
        setSizeFull();
        setBoxSizing(BoxSizing.BORDER_BOX);
        add(
                createPageGridSection());
    }

    private final Grid<DriverData> driversTableData = new Grid<>(DriverData.class, false);
    private final Button addDriverButton = new Button("Add Driver");


    HorizontalLayout createPageHeaderSection() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("header-layout");
        H5 text = new H5("DRIVERS");
        text.addClassNames("driver-header-text");
        addDriverButton.addClassNames("tracker-button", "driver-button");
        addDriverButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        layout.setWidthFull();


        //ADD CLICK LISTENER TO addDriverButton
        addDriverButton.addClickListener(event -> {
           getUI().flatMap(ui -> ui.navigate(AddDriverView.class));
        });

        layout.add(text, addDriverButton);
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        return layout;
    }

    VerticalLayout createPageGridSection() {
        VerticalLayout layout = new VerticalLayout();
        layout.addClassNames("driver-table-layout");
        TextField filterField = new TextField();
        layout.setSizeFull();

        //SET GRID COLUMNS
        driversTableData.addClassNames("driver-table");
        driversTableData.addColumn(DriverData::getDriverId).setHeader("DRIVER ID");
        driversTableData.addColumn(DriverData::getDriverName).setHeader("FULL NAME");
        driversTableData.addColumn(DriverData::getPhoneNumber).setHeader("MOBILE NUMBER");
        driversTableData.addColumn(DriverData::getLicenseType).setHeader("LICENSE TYPE");
        driversTableData.addColumn(DriverData::getExpiryDate).setHeader("EXPIRY DATE");
        driversTableData.addColumn(createStatusLabel()).setHeader("STATUS");
        driversTableData.addColumn(createEditButton()).setHeader("ACTION");

        layout.add(createPageHeaderSection(), filterField, driversTableData);
        return layout;
    }


    //TABLE COMPONENT RENDERERS
    ComponentRenderer<Span, DriverData> createStatusLabel() {
        return new ComponentRenderer<>(item -> {
            Span text = new Span();
            text.getStyle().setFontSize("14");
            if (Objects.equals("active", item.getStatus())) {
                text.setText("Active");
                text.getStyle().setColor("green");
                text.getStyle().setFontWeight(500);
            } else if (Objects.equals("inactive", item.getStatus())) {
                text.setText("In active");
                text.getStyle().setColor("dodgerblue");
                text.getStyle().setFontWeight(500);
            } else {
                text.setText("Deleted");
                text.getStyle().setColor("#ff0000");
                text.getStyle().setFontWeight(500);
            }
            return text;
        });
    }//end of method

    ComponentRenderer<Button, DriverData> createEditButton() {
        return new ComponentRenderer<>(item -> {
            Button button = new Button("Edit");

            button.addThemeVariants(ButtonVariant.LUMO_SMALL);
            button.addClassNames("edit-button");
            return button;
        });
    }//end of method

 }//end of class...
