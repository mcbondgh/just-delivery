package com.app.views.login;

import com.app.views.dashboard.DashboardView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("User Login")
@Route(value = "/login")
@RouteAlias(value = "/user-login")
@Uses(Icon.class)
@JsModule("./js/scripts.js")
public class LoginView extends VerticalLayout {

    public LoginView() {
        VerticalLayout layout = new VerticalLayout(generateLoginForm());
        layout.addClassName("login-layout");

        setWidthFull();
        layout.setWidthFull();
        add(layout);
    }

    private Component generateLoginForm() {
        LoginForm loginForm = new LoginForm();

        loginForm.addClassName("login-form");
        

        loginForm.addLoginListener(event -> {
            // handle successful login
            getUI().ifPresent(ui -> {
                ui.navigate(DashboardView.class);
            });
        });
        return loginForm;
    }
}
