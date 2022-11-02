package me.xurround.mlock.layout.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.collections.FXCollections;
import javafx.beans.property.ObjectProperty;
import me.xurround.mlock.model.AccountRecord;
import me.xurround.mlock.model.ServiceRecord;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExpandableTableRow extends TableRow<ServiceRecord>
{
    private final Pane detailsPane;

    public ExpandableTableRow()
    {
        detailsPane = createDetailsPane(itemProperty());
        selectedProperty().addListener((obs, wasSelected, isNowSelected) ->
        {
            if (isNowSelected)
                getChildren().add(detailsPane);
            else
                getChildren().remove(detailsPane);
            this.requestLayout();
        });
    }

    @Override
    protected double computePrefHeight(double width)
    {
        if (isSelected())
            return super.computePrefHeight(width) + detailsPane.prefHeight(getWidth());
        else
            return super.computePrefHeight(width);
    }

    @Override
    protected void layoutChildren()
    {
        super.layoutChildren();
        if (isSelected())
        {
            double width = getWidth();
            double paneHeight = detailsPane.prefHeight(width);
            detailsPane.resizeRelocate(0, getHeight() - paneHeight, width, paneHeight);
        }
    }

    private Pane createDetailsPane(ObjectProperty<ServiceRecord> item)
    {
        StackPane detailsPane = new StackPane();
        TableView<AccountRecord> acTable = new TableView<>();
        detailsPane.getChildren().add(acTable);
        detailsPane.setStyle("-fx-background-color: -fx-background; -fx-background: skyblue;");

        TableColumn<AccountRecord, String> serviceNameColumn = new TableColumn<>("Service");
        serviceNameColumn.prefWidthProperty().bind(acTable.widthProperty().multiply(0.25));
        TableColumn<AccountRecord, String> serviceUsernameColumn = new TableColumn<>("Username");
        serviceUsernameColumn.prefWidthProperty().bind(acTable.widthProperty().multiply(0.25));
        serviceUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<AccountRecord, String> servicePasswordColumn = new TableColumn<>("Password");
        servicePasswordColumn.prefWidthProperty().bind(acTable.widthProperty().multiply(0.25));
        servicePasswordColumn.setCellValueFactory(dataFeatures ->
            new SimpleStringProperty("\u2022".repeat(dataFeatures.getValue().getClearPasswordLength())));
        TableColumn<AccountRecord, Integer> serviceRegistrationDateColumn = new TableColumn<>("Registration Date");
        serviceRegistrationDateColumn.prefWidthProperty().bind(acTable.widthProperty().multiply(0.25).subtract(4));
        serviceRegistrationDateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        acTable.getColumns().addAll(serviceNameColumn, serviceUsernameColumn, servicePasswordColumn, serviceRegistrationDateColumn);

        acTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        acTable.getSelectionModel().setCellSelectionEnabled(true);

        acTable.getStyleClass().add("noHeader");

        item.addListener((obs, oldItem, newItem) ->
        {
            if (newItem != null)
            {
                if (newItem.getAccounts().size() == 0)
                    detailsPane.setVisible(false);
                acTable.setItems(FXCollections.observableList(newItem.getAccounts()));
                detailsPane.setPrefHeight(newItem.getAccounts().size() * 30 + 1);
            }
        });

        //Allow copying

        MenuItem copyItem = new MenuItem("Copy password");
        copyItem.setOnAction(e ->
        {
            AccountRecord account = acTable.getSelectionModel().getSelectedItem();
            if (account != null)
            {
                ClipboardContent content = new ClipboardContent();
                content.putString(account.getClearPassword());
                Clipboard.getSystemClipboard().setContent(content);
            }
        });
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(copyItem);
        acTable.setContextMenu(contextMenu);

        return detailsPane;
    }
}
