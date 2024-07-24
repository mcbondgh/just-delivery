package com.app.views.customers;

import com.app.data.CustomerData;
import com.app.layouts.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@PageTitle("Customers")
@Route(value = "views/customers", layout = MainLayout.class)
@RolesAllowed({"ADMIN","STAFF"})
public class CustomersView extends VerticalLayout {
    public CustomersView(){
        addClassNames("page-view");
        setSizeFull();
        addCustomerButtonClicked();
        add(
                createPageInnerSection()
        );
    }

    private final Grid<CustomerData> customerDataGrid = new Grid<>(CustomerData.class, false);
    private final Button addCustomerButton = new Button("Add Customer");
    private final TextField filterField = new TextField();
    /*******************************************************************************************************************
                                                            CREATE CUSTOMERS PAGE
    *******************************************************************************************************************/
    VerticalLayout createPageInnerSection() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addClassNames("customer-inner-page-container");
        layout.setPadding(true);
        customerDataGrid.addClassNames("customer-grid");
        layout.add(createPageHeaderSection(), new Hr(), createDataGridSection());
        return layout;
    }//end of method

    //PAGE HEADER SECTION
    HorizontalLayout createPageHeaderSection() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.setClassName("customer-header-container");
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        H5 headerText = new H5("CUSTOMER LIST");
        addCustomerButton.addClassNames("tracker-button", "add-customer-button");
        addCustomerButton.addThemeVariants(ButtonVariant.LUMO_SMALL);

        layout.add(headerText, addCustomerButton);
        return layout;
    }//end of method

    //CUSTOMERS TABLE SECTION
    VerticalLayout createDataGridSection() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addClassNames("customer-grid-container");

        //ADD TEXT-FIELD TO ALLOW TABLE FILTERING.
        filterField.addClassNames("customer-table-filter-field");
        filterField.setPlaceholder("Search by customer name or mobile number");
        filterField.setWidthFull();
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        //filter table on value change
        filterField.addValueChangeListener(change -> {
            customerDataGrid.getListDataView().setFilter(filter -> {
                if (filterField.isEmpty()) {
                    return true;
                }
                boolean matchesNumber = filter.getMobileNumber().toLowerCase().matches(change.getValue().toLowerCase());
                boolean matchesName = filter.getFullname().toLowerCase().matches(change.getValue().toLowerCase());
                return matchesNumber || matchesName;
            });
        });

        customerDataGrid.setClassName("customers-table");
        customerDataGrid.addColumn(CustomerData::getCustomerId).setHeader("CUSTOMER ID").setSortable(true);
        customerDataGrid.addColumn(CustomerData::getFullname).setHeader("FULL NAME").setSortable(true);
        customerDataGrid.addColumn(CustomerData::getMobileNumber).setHeader("MOBILE NUMBER");
        customerDataGrid.addColumn(CustomerData::getEmail).setHeader("EMAIL");
        customerDataGrid.addColumn(createStatusLabel()).setHeader("STATUS");
        customerDataGrid.addColumn(createEditButton()).setHeader("ACTION");

        customerDataGrid.getColumns().forEach(item -> item.setAutoWidth(true));
//        customerDataGrid.getStyle().setFontSize("14px");

        layout.add(filterField, customerDataGrid);
        return layout;
    }//end method

    /*******************************************************************************************************************
     COMPONENT RENDERS
     *******************************************************************************************************************/
    ComponentRenderer<Span, CustomerData> createStatusLabel() {
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

    ComponentRenderer<Button, CustomerData> createEditButton() {
        return new ComponentRenderer<>(item -> {
            Button button = new Button("Edit", event -> {
                String name = item.getFullname();
                Notification.show(String.format("CUSTOMER NAME IS %s", name), 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            });
            button.addThemeVariants(ButtonVariant.LUMO_SMALL);
            button.addClassNames("edit-button");
        return button;
        });
    }//end of method



    /*******************************************************************************************************************
        ACTION EVENTS IMPLEMENTATION
     *******************************************************************************************************************/
    void addCustomerButtonClicked() {
        addCustomerButton.addClickListener(click -> {
            //ADD TEST DATA TO GRID TO TEST ACTION BUTTON
            AtomicInteger counter = new AtomicInteger();
            List<CustomerData> data = new ArrayList<>();
            data.add(new CustomerData(counter.incrementAndGet(), "Customer One", "048349849", "GH-84394-", "example@example.com", "active"));
            data.add(new CustomerData(counter.incrementAndGet(), "Customer Two", "0884838483", "AG-4934-43", "example.didis.com", "inactive"));
            data.add(new CustomerData(counter.incrementAndGet(), "Customer Three", "343434", "AG-49344-00", "example.com", "disable"));
            customerDataGrid.setItems(data);
        });
    }//end of method...




}//end of class...
