package com.app.views.drivers;

import com.app.layouts.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Assign Driver")
@Route(value = "views/assign-driver", layout = MainLayout.class)
@RolesAllowed({"ADMIN", "STAFF"})
public class DeliveryRouteView extends VerticalLayout implements BeforeEnterObserver, AfterNavigationObserver{
    public DeliveryRouteView(){}


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
//        getUI().get().access(() -> {
//            event.getUI().getPage().executeJs("showAlert()");
//        });
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
       
    }
}//end of class...
