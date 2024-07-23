package com.app.views.staff;

import com.app.layouts.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Staff Members")
@Route(value = "views/staff-members", layout = MainLayout.class)
public class StaffMembersView extends VerticalLayout {

    public StaffMembersView() {
        setSizeFull();
        setSpacing(true);
    }


}//end of class
