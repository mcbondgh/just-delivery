package com.app.views.notifications;


import com.app.layouts.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Notifications")
@Route(value = "views/notifications", layout = MainLayout.class)
@Uses(Icon.class)
public class NotificationView {
}
