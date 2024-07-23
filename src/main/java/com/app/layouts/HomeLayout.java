package com.app.layouts;

//import com.app.views.dashboard.DashboardView;
//import com.app.views.login.LoginView;
//import com.app.views.registration.RegistrationView;
import com.app.views.dashboard.DashboardView;
import com.app.views.login.LoginView;
import com.app.views.registration.RegistrationView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;

public class HomeLayout extends AppLayout{

    private final Image siteLogo = new Image("icons/main-logo.png", "JUST-DELIVERY");

    public HomeLayout() {

        FlexLayout navigation = navigationLayout();
        navigation.getElement();
        addToNavbar(true, navigation);
        setComponentClassNames();
        setClassName("homepage-layout");
    }


    private void setComponentClassNames() {
        siteLogo.setClassName("site-logo");

    }


    //design and add links to horizontal layout
    private FlexLayout navigationLayout() {
        FlexLayout layout = new FlexLayout();
        Anchor anchor = new Anchor("homepage", siteLogo);
        String link1 = RegistrationView.class.getAnnotation(Route.class).value();;
//        String link2 = LoginView.class.getAnnotation(Route.class).value();
        String link3 = LoginView.class.getAnnotation(Route.class).value();

        Anchor loginLink = new Anchor(link3, "Login");
        Anchor registerLink = new Anchor(link1, "Register");


        MenuBar menuBar = new MenuBar();
        menuBar.setClassName("menu-bar");
        menuBar.addThemeVariants(MenuBarVariant.LUMO_END_ALIGNED);
        menuBar.addItem(loginLink).addClassNames("nav-link", "login-link");
        menuBar.addItem(registerLink).addClassNames("nav-link", "register-link");

        layout.setClassName("flex-header-container");
        anchor.setClassName("site-anchor-logo");
        layout.add(anchor, menuBar);
        return layout;
    }

    public FlexLayout createFooter() {
        FlexLayout layout = new FlexLayout();
        layout.setWidthFull();
        layout.addClassNames("main-layout-footer");

        return layout;
    }












}
