package com.app.layouts;

import com.app.views.customers.CustomerProfileView;
import com.app.views.customers.CustomersView;
import com.app.views.dashboard.DashboardView;
import com.app.views.drivers.*;
import com.app.views.invoice.OrderInvoicesView;
import com.app.views.invoice.ReportsView;
import com.app.views.orders.CreateOrderView;
import com.app.views.orders.OrdersView;
import com.app.views.payments.PaymentHistoryView;
import com.app.views.settings.InvoiceSettings;
import com.app.views.staff.AddStaffMemberView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.jetbrains.annotations.NotNull;
import org.vaadin.lineawesome.LineAwesomeIcon;


/**
 * The main view is a top-level placeholder for other views.
 */

@JsModule("./js/scripts.js")
@Uses(Icon.class)
public class MainLayout extends AppLayout {

    private H1 viewTitle;
    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span("JUST DELIVERY");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);
        header.addClassNames("sidebar-header-text");

        Scroller scroller = new Scroller(createNavigation());
        addToDrawer(header, scroller, createFooter());
    }

    /*NAVIGATION MENU ITEMS
    ..............................................................................................................
     */
    //driver side nav items
    private static @NotNull SideNav driverSideNavItems() {
        SideNav sideNav = new SideNav("Driver Manager");
        SideNavItem addDriver = new SideNavItem("Drivers", DriversView.class, LineAwesomeIcon.CAR_SIDE_SOLID.create());
        SideNavItem driverProfile = new SideNavItem("Driver Profile", DriverProfileView.class, LineAwesomeIcon.CAR_ALT_SOLID.create());
        SideNavItem assignDriver = new SideNavItem("Delivery Routs", DeliveryRouteView.class, LineAwesomeIcon.ASTERISK_SOLID.create());
        SideNavItem viewAssignedDrivers = new SideNavItem("Assigned Packages", AssignedPackagesView.class, LineAwesomeIcon.MOTORCYCLE_SOLID.create());
        SideNavItem ratingAndPerformance = new SideNavItem("Customer Rating", CustomerRatingView.class, LineAwesomeIcon.STAR_SOLID.create());
        sideNav.addItem(addDriver, assignDriver, viewAssignedDrivers, driverProfile, ratingAndPerformance);
        return sideNav;
    }

    //customer side nav items
    private static @NotNull SideNav customerSideNavItems() {
        SideNav customersNav = new SideNav("Customers Manager");
        SideNavItem customersListButton = new SideNavItem("My Customers", CustomersView.class, VaadinIcon.USER_CLOCK.create());
        SideNavItem customerProfileButton = new SideNavItem("Customer Profile", CustomerProfileView.class, VaadinIcon.USER.create());
        customersNav.addItem(customersListButton);
        customersNav.addItem(customerProfileButton);
        return customersNav;
    }

    //dashboard side nav item
    private static @NotNull SideNav dashboardSideNavItem() {
        SideNav nav = new SideNav();
        SideNavItem dashboardButton = new SideNavItem("Dashboard", DashboardView.class, VaadinIcon.DASHBOARD.create());
        nav.addItem(dashboardButton);
        return nav;
    }

    private static @NotNull SideNav staffSideNavItem() {
        SideNav nav = new SideNav();
        Avatar staffIcon = new Avatar("img", "icons/staff-100.png");
        staffIcon.addClassNames("nav-icon");
        SideNavItem staffNav = new SideNavItem("Staff Members", AddStaffMemberView.class, staffIcon);
        nav.addItem(staffNav);
        return nav;
    }

    private static @NotNull SideNav paymentHistoryNavItem() {
        SideNav nav = new SideNav();
        Avatar paymentIcon = new Avatar("img", "icons/payment-history.png");
        paymentIcon.addClassNames("nav-icon");
        SideNavItem paymentsNav = new SideNavItem("Payment History", PaymentHistoryView.class, paymentIcon);
        nav.addItem(paymentsNav);
        return nav;
    }

    private static @NotNull SideNav invoiceSideNavItem() {
        SideNav nav = new SideNav();
        Avatar invoiceIcon = new Avatar("img", "icons/icons8-invoice.png");
        invoiceIcon.addClassNames("nav-icon");
        SideNavItem invoicesNav = new SideNavItem("Reports", ReportsView.class, invoiceIcon);
        nav.addItem(invoicesNav);
        nav.setWidthFull();
        return nav;
    }

    private static @NotNull SideNav ordersSideNavItems() {
        SideNav sideNav = new SideNav("Oder Manager");
        sideNav.addItem(new SideNavItem("View Orders", OrdersView.class, VaadinIcon.CHART_GRID.create()));
        sideNav.addItem(new SideNavItem("Create Order", CreateOrderView.class, VaadinIcon.PACKAGE.create()));
        SideNavItem orderDetailsButton = new SideNavItem("Order Invoices", OrderInvoicesView.class, VaadinIcon.ARCHIVE.create());
        sideNav.addItem(orderDetailsButton);
        return sideNav;
    }

    private static @NotNull SideNav settingsSideNavItem() {
        SideNav settings = new SideNav("Settings");
        SideNavItem settingsNav = new SideNavItem("Invoice Settings", InvoiceSettings.class, LineAwesomeIcon.SCREWDRIVER_SOLID.create());
        settings.addItem(settingsNav);
        settings.setWidthFull();
        return settings;
    }

    //SET NAVIGATION ITEMS TO SIDEBAR
    private Component createNavigation() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setClassName("side-bar-layout");

        //SIDE NAVIGATION
        SideNav dashboardNav = dashboardSideNavItem();
        //ADD NAVIGATION LINKS TO THE ORDERS SIDE NAV
        SideNav ordersNav = ordersSideNavItems();
        //ADD NAVIGATION LINKS TO DRIVER MANAGER
        SideNav driversNav = driverSideNavItems();
        //ADD NAVIGATION LINK TO CUSTOMERS MANAGER
        SideNav customersNav = customerSideNavItems();
        //ADD LINKS TO NOTIFICATION NAV
        SideNav notificationNav = new SideNav("Notifications");
        //ADD STAFF NAVIGATION LINK
        SideNav staffNav = staffSideNavItem();
        //ADD PAYMENT-HISTORY LINK
        SideNav paymentNav = paymentHistoryNavItem();
        //ADD INVOICE NAVIGATION LINK
        SideNav invoiceNav = invoiceSideNavItem();
        //CREATE SETTINGS SIDE NAV
        SideNav settingsNav = settingsSideNavItem();
    

        dashboardNav.setWidthFull();
        ordersNav.setCollapsible(true);
        ordersNav.setExpanded(false);
        ordersNav.setWidthFull();
        driversNav.setWidthFull();
        paymentNav.setWidthFull();
        invoiceNav.setWidthFull();
        notificationNav.setWidthFull();
        notificationNav.setCollapsible(true);
        notificationNav.setExpanded(false);
        driversNav.setCollapsible(true);
        driversNav.setExpanded(false);
        customersNav.setCollapsible(true);
        customersNav.setExpanded(false);
        customersNav.setWidthFull();
        staffNav.setWidthFull();
        ordersNav.addClassNames("side-nav-item");
        customersNav.addClassNames("side-nav-item");
        driversNav.addClassNames("side-nav-item");
        notificationNav.addClassNames("side-nav-item");
        paymentNav.addClassNames("side-nav-item");
        invoiceNav.addClassNames("side-nav-item");
        staffNav.addClassNames("side-nav-item");
        settingsNav.setWidthFull();
        settingsNav.addClassName("side-nav-item");
        settingsNav.setExpanded(false);
        settingsNav.setCollapsible(true);
        
        layout.add(
                dashboardNav, staffNav, paymentNav, invoiceNav,
                notificationNav, ordersNav, driversNav, customersNav,
                settingsNav);

        return layout;
    }

    //SIDE NAV FOOTER
    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("main-layout-footer");
        MenuBar menuBar = new MenuBar();
        menuBar.addClassNames("footer-menu-bar");
        H5 USERNAME = new H5("JUST DELIVERY");

        USERNAME.addClassNames("footer-text");
        Avatar profilePicture = new Avatar("user", "images/user-100.png");
        profilePicture.addClassNames("picture-icon");
        menuBar.getStyle().setPadding("0 3px");
        menuBar.getElement().getStyle().set("background-color", "transparent");
        MenuItem signOutButton = menuBar.addItem(profilePicture);
        signOutButton.add(USERNAME);
        Anchor anchor = new Anchor("/", "Sing out");
        signOutButton.addClassNames("menu-item");
        signOutButton.getSubMenu().addItem(anchor, e-> {
        });

        layout.add(menuBar);
        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}