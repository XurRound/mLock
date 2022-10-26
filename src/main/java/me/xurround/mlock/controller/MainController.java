package me.xurround.mlock.controller;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import me.xurround.mlock.App;
import me.xurround.mlock.layout.components.ExpandableTableRow;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.model.AccountRecord;
import me.xurround.mlock.model.ServiceRecord;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Function;

public class MainController implements Initializable
{
    @FXML
    private TableView<ServiceRecord> pmTable;

    @FXML
    private Label addBT;

    @FXML
    private Label removeBT;

    @FXML
    private Label editBT;

    @FXML
    private Label settingsBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        addBT.setOnMouseClicked(e ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.ACCOUNT_ADD, TransitionType.SLIDE_LEFT);
        });

        settingsBT.setOnMouseClicked(e ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.SETTINGS, TransitionType.SLIDE_RIGHT);
        });

        pmTable.setRowFactory(itemTableView -> new ExpandableTableRow());

        pmTable.getItems().addAll();

        TableColumn<ServiceRecord, String> serviceNameColumn = new TableColumn<>("Service");
        serviceNameColumn.setResizable(false);
        serviceNameColumn.setReorderable(false);
        serviceNameColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        TableColumn<ServiceRecord, String> serviceUsernameColumn = new TableColumn<>("Username");
        serviceUsernameColumn.setResizable(false);
        serviceUsernameColumn.setReorderable(false);
        serviceUsernameColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        TableColumn<ServiceRecord, String> servicePasswordColumn = new TableColumn<>("Password");
        servicePasswordColumn.setResizable(false);
        servicePasswordColumn.setReorderable(false);
        servicePasswordColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        TableColumn<ServiceRecord, Integer> serviceRegistrationDateColumn = new TableColumn<>("Registration Date");
        serviceRegistrationDateColumn.setResizable(false);
        serviceRegistrationDateColumn.setReorderable(false);
        serviceRegistrationDateColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25).subtract(2));

        pmTable.getColumns().addAll(serviceNameColumn, serviceUsernameColumn, servicePasswordColumn, serviceRegistrationDateColumn);

        pmTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pmTable.setItems(App.getInstance().getDataManager().getPasswordStorage().getRecords());
    }
}
