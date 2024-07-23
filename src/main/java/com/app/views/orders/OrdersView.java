package com.app.views.orders;

import com.app.custom.ComponentLoader;
import com.app.data.OrdersData;
import com.app.layouts.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Orders")
@Route(value = "views/orders", layout = MainLayout.class)
public class OrdersView extends VerticalLayout {

    public OrdersView() {
        setSpacing(false);
        setSizeFull();
        add(createViewHeader(), createTableSection());
    }

    private final TextField filterField = new TextField();
    private final ComboBox<Integer>limitSelector = new ComboBox<>("Set Limit");
    private final Grid<OrdersData> ordersTable = new Grid<>(OrdersData.class, false);

    /*******************************************************************************************************************
                PAGE HEADER SECTION
     *******************************************************************************************************************/
    private FlexLayout createViewHeader() {
        H6 headerTitle = new H6("CREATED ORDERS");
        headerTitle.setClassName("orders-header-text");
        filterField.addClassNames("filter-field");
        limitSelector.addClassNames("item-label", "limit-selector");
        filterField.setPlaceholder("Filter by sender, receiver, email or orderNo");
        filterField.setWidthFull();

        ComponentLoader.setTableLimit(limitSelector);
        limitSelector.setValue(50);
        Div filterAndLimitContainer = new Div(filterField, limitSelector);
        filterAndLimitContainer.addClassNames("filter-and-limit-container");
        FlexLayout layout = new FlexLayout(headerTitle, filterAndLimitContainer);
        layout.addClassNames("orders-header-container");
        layout.setWidthFull();

        return layout;
    }


    /*******************************************************************************************************************
        TABLE SECTION
     *******************************************************************************************************************/

    private VerticalLayout createTableSection() {
        VerticalLayout layout = new VerticalLayout(ordersTable);
        layout.setSpacing(true);
        layout.setBoxSizing(BoxSizing.BORDER_BOX);
        layout.setSizeFull();
        ordersTable.addClassNames("table-grid", "orders-table");
        ordersTable.setSizeFull();
        setTableColumns();
        return layout;
    }

    /*******************************************************************************************************************
     OTHER METHODS SECTION
     *******************************************************************************************************************/
    void setTableColumns() {
        ordersTable.addColumn(OrdersData::getOrderId).setHeader("ORDER No.");
        ordersTable.addColumn(OrdersData::getSenderName).setHeader("SENDER");
        ordersTable.addColumn(OrdersData::getReceiverName).setHeader("RECEIVER");
        ordersTable.addColumn(OrdersData::getPickupAddress).setHeader("PICKUP ADDRESS");
        ordersTable.addColumn(OrdersData::getDeliveryAddress).setHeader("DELIVERY ADDRESS");
        ordersTable.addColumn(OrdersData::getDateCreated).setHeader("DATE CREATED");
        ordersTable.addColumn(OrdersData::orderStatus).setHeader("DELIVERY STATUS");
        ordersTable.addColumn(OrdersData::assignDeliveryManButton).setHeader("ASSIGN");
        ordersTable.addColumn(OrdersData::cancelOrViewOrderButtons).setHeader("ACTION");
        ordersTable.getColumns().forEach(item-> item.setAutoWidth(true));
    }

}//END OF CLASS...
