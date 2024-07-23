package com.app.views.drivers;

import com.app.data.OrdersData;
import com.app.layouts.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.hibernate.query.Order;

@PageTitle("Assigned Packages")
@Route(value = "views/assigned-package", layout = MainLayout.class)
@RolesAllowed({"ADMIN","STAFF","RIDER"})
public class AssignedPackagesView extends VerticalLayout {
    public AssignedPackagesView() {
        addClassNames("page-view");
        setSizeFull();
        add(createHeaderSection(), createTableSection());
    }

    private final Grid<OrdersData> ordersTable = new Grid<>(OrdersData.class, false);
    private final TextField filterField = new TextField();
    Component createHeaderSection() {
        H5 textHeader = new H5("Your Assigned Orders");
        textHeader.addClassNames("header-text");
        FlexLayout layout = new FlexLayout(textHeader);
        layout.addClassNames("header-container");
        return layout;
    }

    Component createTableSection() {
        filterField.setClassName("filter-field");
        filterField.setPlaceholder("Filter by order number or name");
        VerticalLayout layout = new VerticalLayout(filterField, ordersTable);
        layout.setSizeFull();
        layout.addClassNames("page-container");
        configureTableColumns();
        return layout;
    }

    void configureTableColumns() {
        ordersTable.addClassNames("table-grid", "assigned-orders-table");
        ordersTable.addColumn(OrdersData::getOrderNo).setHeader("ORDER NO.");
        ordersTable.addColumn(OrdersData::getReceiverName).setHeader("RECEIVER");
        ordersTable.addColumn(OrdersData::getPickupAddress).setHeader("PICKUP ADDRESS");
        ordersTable.addColumn(OrdersData::getDeliveryAddress).setHeader("DESTINATION ADDRESS");
        ordersTable.addColumn(OrdersData::getSenderMobileNumber).setHeader("RECEIVER'S NUMBER");
        ordersTable.addColumn(statusComponent()).setHeader("STATUS");
        ordersTable.addColumn(viewButton()).setHeader("ACTION");
        ordersTable.getColumns().forEach(item -> item.setAutoWidth(true));
    }


    /*******************************************************************************************************************
                COMPONENT RENDERERS
     *******************************************************************************************************************/
    private ComponentRenderer<Span, OrdersData> statusComponent() {
        return new ComponentRenderer<>(dataItem -> {
            Span span = new Span("Status");

            return span ;
        });
    }

    private ComponentRenderer<Button, OrdersData> viewButton() {
        return new ComponentRenderer<>(dataItem -> {
           Button button = new Button("View");

           return button;
        });
    }








}//end of class..
