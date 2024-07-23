package com.app.views.orders;

import com.app.layouts.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;


@PageTitle("Order Details")
@Route(value = "views/order/orderdetails", layout = MainLayout.class)
public class OrderDetailsView extends Composite<VerticalLayout> implements BeforeEnterObserver{

    public static final String orderNumber = null;
    public OrderDetailsView() {
    }

    @Override
    public void beforeEnter(@OptionalParameter  BeforeEnterEvent event) {
    }


}//end of class..
