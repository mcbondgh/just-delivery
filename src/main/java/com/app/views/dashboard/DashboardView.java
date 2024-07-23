package com.app.views.dashboard;

import com.app.layouts.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Dashboard")
@Route(value = "/dashboard", layout = MainLayout.class)
@Uses(Icon.class)
public class DashboardView extends Composite<VerticalLayout> {
    public DashboardView() {
        addClassName("page-view");
        getContent().setSizeFull();
        getContent().add(pageContentSection());
    }

    private String [] cardNames = {"Registered Customers","Registered Drivers", "Total Orders", "Assigned Orders", "Placed Orders", "Dispatched Packages", "Delivered Packages", "Cancelled Orders"};
    private int[] orderCounters = {20, 6, 120, 100, 10, 50, 12, 9};

    private Component pageContentSection() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassNames("card-page-container");
        layout.setSizeFull();

        int counterSize = orderCounters.length;
        for (int i = 0; i < counterSize; i++) {
            String labelName = cardNames[i];
            int counterValue = orderCounters[i];
            layout.add(createCard(labelName, counterValue));
        }
        return layout;
    }



    private Div createCard(String labelName, int itemCount) {
        Div div = new Div();
        div.addClassNames("card", "card-container");
        Span cardLabel = new Span(labelName);
        cardLabel.addClassNames("card", "card-label");
        H5 dataCounters = new H5(String.valueOf(itemCount));
        dataCounters.addClassNames("card", "card-data-counter");
        Div cardInfo = new Div();
        Image cardIcon = new Image("icons/icons8-delivery-64.png", "img");
        cardInfo.addClassNames("card", "card-info-container");
        cardIcon.addClassNames("card-icon");

        cardInfo.add(cardLabel, dataCounters);
        div.add(cardInfo, cardIcon);
        return div;
    }


}//end of class...
