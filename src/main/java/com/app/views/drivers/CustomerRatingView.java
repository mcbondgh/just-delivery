package com.app.views.drivers;

import com.app.layouts.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Customer Rating")
@Route(value = "views/customer-rating", layout = MainLayout.class)
@RolesAllowed({"ADMIN", "STAFF"})
public class CustomerRatingView extends VerticalLayout {

    public CustomerRatingView() {}
}
