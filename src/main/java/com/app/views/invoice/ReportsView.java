package com.app.views.invoice;

import com.app.layouts.MainLayout;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Reports")
@Route(value = "views/reports", layout = MainLayout.class)
public class ReportsView extends VerticalLayout {

    public ReportsView() {

    }

    private DatePicker startDate = new DatePicker("Start Date");
    private DatePicker endDate = new DatePicker("End Date");
    private Grid<Object> reportsTable = new Grid<>();



}//END OF CLASS..
