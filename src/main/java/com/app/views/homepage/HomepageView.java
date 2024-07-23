package com.app.views.homepage;

import com.app.layouts.HomeLayout;
import com.app.views.packages.PackageTrackerView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.vaadin.lineawesome.LineAwesomeIcon;

import static com.app.custom.CustomComponents.customResponsiveLayout;
import static com.app.custom.CustomComponents.defaultFormColumns;


@PageTitle("Homepage")
@Route(value = "", layout=HomeLayout.class)
@RouteAlias(value = "homepage", layout = HomeLayout.class)
@Uses(Icon.class)
public class HomepageView extends VerticalLayout {

    public HomepageView() {
        getStyle().setBoxSizing(Style.BoxSizing.BORDER_BOX);
        setClassName("homepage-container");
       add(
               heroSection(),
               servicesSection(),
               contactSection(),
               createFooter()
       );
       navigationFunction();
    }

/*********************************************************************************************************************
 ******************** VIEW COMPONENT SECTION
 *********************************************************************************************************************/


    private Component heroSection() {
        VerticalLayout layout = new VerticalLayout();

        H4 welcomeText = new H4("WELCOME TO");
        H1 justText = new H1("JUST ");
        H1 deliveryText = new H1(" DELIVERY");
        Span sloganText = new Span("anything movable, we deliver...");
        Button trackerButton = new Button("Track Package");

        FlexLayout flexLayout = new FlexLayout( justText, deliveryText);
        flexLayout.setAlignItems(Alignment.CENTER);
        //SET COMPONENT CLASS NAMES
        layout.setWidthFull();
        layout.setClassName("hero-container");
        welcomeText.addClassNames("hero-item", "welcome-text");
        flexLayout.setClassName("joint-text-container");
        sloganText.addClassNames("hero-item", "slogan-text");
        justText.addClassNames("hero-item", "just-text");
        deliveryText.addClassNames("hero-item", "delivery-text");
        trackerButton.setClassName("tracker-button");

        /***************************************************************************************************************
                ACTION EVENT FOR TRACKER BUTTON CLICKED.
         **************************************************************************************************************/
        try {
            trackerButton.addClickListener(click -> {
                this.getUI().flatMap(ui -> ui.navigate(PackageTrackerView.class));
                UI.getCurrent().getPage().executeJs("showAlert($0, $1)", "PRINCE", "Mcbondgh");
            });
        }catch (Exception e){e.printStackTrace();}


        layout.add(welcomeText, flexLayout, sloganText, trackerButton);
        return layout;
    }

    private Component servicesSection() {
        VerticalLayout section = new VerticalLayout();
        H2 sectionTitle = new H2("OUR SERVICES");

        sectionTitle.addClassNames("section-title", "service-section-container");
        section.setClassName("section-container");

        FormLayout formLayout = new FormLayout();
        formLayout.addClassNames("form-layout", "services-form-layout");

        H5 itemTitle1 = new H5("PICK-UP AND DELIVERY");
        H5 itemTitle2 = new H5("PACKAGING, LOADING & UNLOADING");
        H5 itemTitle3 = new H5("DISTRESS PACKAGE");

        Paragraph itemContent1 = new Paragraph("""
                Time shown for pick-up and delivery is an estimate.\s
                We do not guarantee that actual pick-up and delivery time will meet customer expectation,\s
                and we shall not be liable for any delay in delivery of the package or for any loss,\s
                damage or deterioration arising from the delay,\s
                unless such delay or deterioration is attributable to wilful default or gross negligence.
               \s""");

        Paragraph itemContent2 = new Paragraph("""
                The Customer shall be responsible for the packaging of the goods.\s
                The goods shall be packaged in such a way so as to with-stand normal\s
                transport-handling, and not cause damage to other goods.        \s
                We shall not be liable for any damage resulting from the absence of,\s
                or defects in, packing material or other packaging.
               \s""");

        Paragraph itemContent3 = new Paragraph("""
                A package is distressed if we are unable to make delivery nor return\s
                it to the ordering Customer due to payment issues, or any other reasons attributable\s
                to the customer that require us to hold the package for a reasonable period of
                 time beyond estimated transit time
               \s""");

        Div container1 = new Div(itemTitle1, itemContent1);
        Div container2 = new Div(itemTitle2, itemContent2);
        Div container3 = new Div(itemTitle3, itemContent3);

        container1.setClassName("service-div");
        container2.setClassName("service-div");
        container3.setClassName("service-div");

        formLayout.add(container1, container2, container3);
        customResponsiveLayout(formLayout);

        section.add(sectionTitle, formLayout);
        return section;
    }

    private Component contactSection() {
        VerticalLayout flexLayout = new VerticalLayout();
        flexLayout.addClassNames("section-container", "contact-section-container");

        H2 header = new H2("SEND US A MESSAGE");

        Div container1 = new Div(generateContactForm());

        FormLayout formLayout = new FormLayout(container1, setContactInformation());
        formLayout.setClassName("contact-section-formlayout");
        defaultFormColumns(formLayout);

        flexLayout.add(header, formLayout);
        return flexLayout;
    }

    public FlexLayout createFooter() {
        FlexLayout layout = new HomeLayout().createFooter();
        Span span = new Span("2024 (c) JUST DELIVERY");
        Image logo = new Image("images/delivery-logo.png", "JUST DELIVERY");

        span.setClassName("footer-text");
        logo.setClassName("footer-logo");

        layout.setWidthFull();
        layout.setClassName("footer-container");

        layout.add(logo, span);
        return layout;
    }


/***********************************************************************************************************************
 * IMPLEMENTATION OF OTHER METHODS
 ***********************************************************************************************************************/
    Div setContactInformation() {
        Div container = new Div();
        container.setWidthFull();
        container.setClassName("contact-info-section");

        Span heroText = new Span("Contact Information");
        Paragraph locationContent = new Paragraph("""
                GPS ADDRESS: GP-2234-0003, \n OFFICE ADDRESS: The Royal St. 3234 HoodLane\nGhana
                """);
        Anchor email = new Anchor("mailto:justdelivery@example.com",
                "justdelivery@example.com");
        Span mailContent = new Span("Send us a direct mail\n");

        container.add(heroText, locationContent, mailContent, email);
        return container;
    }


    Component generateContactForm() {
        Button sendButton = new Button("Send Message");
        TextField nameField = new TextField("Tell Us Your name");
        NumberField numberField = new NumberField("Your Mobile Number");
        EmailField emailField = new EmailField("Your Email");
        TextArea messageField = new TextArea("Your Message");
        FormLayout contactFormLayout = new FormLayout();

        //set component class names
        sendButton.addClassNames("send-button", "contact-send-button");
        nameField.addClassNames("-input-field", "contact-name-field");
        numberField.addClassNames("input-field", "contact-number-field");
        emailField.addClassNames("input-field", "contact-email-field");
        messageField.addClassNames("input-field", "contact-message-field");
        contactFormLayout.addClassNames("form-layout", "contact-form-layout");

        sendButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ICON);
        sendButton.setPrefixComponent(LineAwesomeIcon.PUSHED.create());

        //set configure form columns
        defaultFormColumns(contactFormLayout);
        contactFormLayout.setColspan(sendButton, 2);
        contactFormLayout.setColspan(messageField, 2);
        contactFormLayout.setColspan(nameField, 2);

        //add components to form layout
        contactFormLayout.add(
                nameField, numberField, emailField,
                messageField, sendButton
        );

        //input fields validation
        nameField.setPlaceholder("Eg Ama Sadia");
        nameField.setRequired(true);
        nameField.setInvalid(nameField.isEmpty());

        emailField.setPlaceholder("example@example.com");

        numberField.setRequired(true);
        numberField.setPlaceholder("0200000001");
        numberField.setInvalid(numberField.isEmpty());

        messageField.setRequired(true);
        messageField.setPlaceholder("Write your message here");
        messageField.setInvalid(messageField.isEmpty());

        //action event methods
        return contactFormLayout;
    }

    /*******************************************************************************************************************
        IMPLEMENTATION OF ACTION EVENT LISTENERS
     */

    void navigationFunction() {

    }

}//ens of class...
