package com.app.views.drivers;

import com.app.layouts.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Your Profile")
@Route(value = "views/driver-profile", layout = MainLayout.class)
@RolesAllowed({"ADMIN", "STAFF"})
public class DriverProfileView extends VerticalLayout {

    public DriverProfileView() {
    }
}
