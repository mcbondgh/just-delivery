package com.app.custom;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import org.jetbrains.annotations.NotNull;

public class CustomComponents {

    public CustomComponents() {
    }

    public static void customResponsiveLayout(@NotNull FormLayout formLayout) {
        formLayout.setWidthFull();
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 320px
                new FormLayout.ResponsiveStep("320px", 2),
                // Use three columns, if the layout's width exceeds 500px
                new FormLayout.ResponsiveStep("600px", 3));
    }

    public static void miniFieldsLayout(@NotNull FormLayout formLayout) {
        formLayout.setWidthFull();
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 320px
                new FormLayout.ResponsiveStep("320px", 2),
                // Use three columns, if the layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 3),
                new FormLayout.ResponsiveStep("800px", 5));
    }

    public static void defaultFormColumns(FormLayout formLayout) {
        formLayout.setWidthFull();
    }

    public static void setComponentSpan(@NotNull FormLayout formLayout, int numberOfColumns, Component componentToExpand) {
        formLayout.setColspan(componentToExpand, numberOfColumns);
    }

}
