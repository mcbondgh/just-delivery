package com.app.views.payments;

import com.app.data.PaymentsData;
import com.app.layouts.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.exolab.castor.types.DateTime;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

@PageTitle("Payment History")
@Route(value = "views/payment-history", layout = MainLayout.class)
public class PaymentHistoryView extends VerticalLayout {

    public PaymentHistoryView() {
        setSpacing(true);
        setSizeFull();
        addClassName("page-view");
        add(createHeaderSection(), createTableSection());
    }
    private Grid<PaymentsData> paymentsTable = new Grid<>(PaymentsData.class, false);
    private TextField filterField = new TextField();

    private Component createHeaderSection() {
        H5 headerText = new H5("Payments History");
        FlexLayout layout = new FlexLayout(headerText);
        layout.addClassNames("header-container", "payment-header-layout");
        return layout;
    }

    private Component createTableSection() {
        filterField.addClassNames("filter-field", "payments-filter-field");
        VerticalLayout layout = new VerticalLayout(filterField, paymentsTable);
        layout.setSizeFull();
        layout.addClassNames("page-container");
        configureTableColumns();
        return layout;
    }

    private void configureTableColumns() {
        paymentsTable.addClassNames("table-grid", "payments-table");
        paymentsTable.setSizeFull();
        paymentsTable.addColumn(PaymentsData::getPaymentId).setHeader("PAYMENT ID");
        paymentsTable.addColumn(PaymentsData::getOrderNo).setHeader("ORDER NUMBER");
        paymentsTable.addColumn(PaymentsData::getPaymentMethod).setHeader("PAYMENT METHOD");
        paymentsTable.addColumn(PaymentsData::getPaymentStatus).setHeader("STATUS");
        paymentsTable.addColumn(PaymentsData::getPaymentDate).setHeader("PAYMENT DATE");
        paymentsTable.addColumn(createViewButton()).setHeader("ACTION");
        paymentsTable.getColumns().forEach(item -> item.setAutoWidth(true));
    }

    private ComponentRenderer<Button, PaymentsData> createViewButton() {
        return new ComponentRenderer<>(itemData -> {
            Button button = new Button("view");

            return button;
        });
    }

}//end of class..
