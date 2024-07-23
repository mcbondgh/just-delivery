package com.app.custom;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CustomNotifications {

    private final String content;;
    private final Avatar icon = new Avatar();
    private Notification notification;
    private HorizontalLayout layout = new HorizontalLayout();
    public CustomNotifications(String message) {
        layout.setSizeFull();
        notification.getStyle().set("width", "500px");
        icon.getStyle().set("width", "20px");
        this.content = message;
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        layout.setSpacing(true);
        Span span = new Span(content);
        span.getStyle().setFontSize("small");
        span.setWidthFull();
        layout.add(icon, span);
    }
    public void success() {
        notification = new Notification(content, 3000);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.setPosition(Notification.Position.TOP_END);
        icon.setImage("icons/success-white.png");
        notification.add(layout);
        notification.open();
    }
    public void warning() {
        notification = new Notification(content, 3000);
        notification.addThemeVariants(NotificationVariant.LUMO_WARNING);
        notification.setPosition(Notification.Position.TOP_END);
        icon.setImage("icons/icons8-warning-50.png");
        notification.add(layout);
        notification.setText(content);
    }

    public void error() {
        notification = new Notification(content, 3000);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.TOP_END);
        icon.setImage("icons/icons8-cancel-24.png");
        notification.add(layout);
        Notification.show(content);
    }
}
