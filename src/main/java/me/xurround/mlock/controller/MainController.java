package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import me.xurround.mlock.App;
import me.xurround.mlock.layout.components.ExpandableTableRow;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.model.ServiceRecord;
import me.xurround.mlock.settings.LocalizationManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private TableView<ServiceRecord> pmTable;

    @FXML
    private TextField searchTF;

    @FXML
    private Label addBT;

    @FXML
    private Label logoutBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        LocalizationManager localizationManager = App.getInstance().getLocalizationManager();

        addBT.setOnMouseClicked(e ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.ACCOUNT_ADD, TransitionType.SLIDE_LEFT);
        });

        logoutBT.setOnMouseClicked(e ->
        {
            App.getInstance().getProfileManager().logout();
            App.getInstance().getSceneManager().setLayout(AppScene.LOGIN, TransitionType.SLIDE_RIGHT);
        });

        searchTF.textProperty().addListener((observable, oldValue, newValue) ->
        {
            pmTable.getSelectionModel().clearSelection();
            if (newValue.isBlank())
                return;
            pmTable.getItems().stream().filter(item -> item.getServiceName().toLowerCase().contains(newValue.toLowerCase())).findAny().ifPresent(item ->
            {
                pmTable.getSelectionModel().select(item);
                pmTable.scrollTo(item);
            });
        });

        pmTable.setRowFactory(itemTableView -> new ExpandableTableRow());

        TableColumn<ServiceRecord, String> serviceNameColumn = new TableColumn<>(localizationManager.getLocalizedString("service"));
        serviceNameColumn.setResizable(false);
        serviceNameColumn.setReorderable(false);
        serviceNameColumn.setSortable(false);
        serviceNameColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        TableColumn<ServiceRecord, String> serviceUsernameColumn = new TableColumn<>(localizationManager.getLocalizedString("username"));
        serviceUsernameColumn.setResizable(false);
        serviceUsernameColumn.setReorderable(false);
        serviceUsernameColumn.setSortable(false);
        serviceUsernameColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        TableColumn<ServiceRecord, String> servicePasswordColumn = new TableColumn<>(localizationManager.getLocalizedString("password"));
        servicePasswordColumn.setResizable(false);
        servicePasswordColumn.setReorderable(false);
        servicePasswordColumn.setSortable(false);
        servicePasswordColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        TableColumn<ServiceRecord, Integer> serviceRegistrationDateColumn = new TableColumn<>(localizationManager.getLocalizedString("registration_date"));
        serviceRegistrationDateColumn.setResizable(false);
        serviceRegistrationDateColumn.setReorderable(false);
        serviceRegistrationDateColumn.setSortable(false);
        serviceRegistrationDateColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25).subtract(2));

        pmTable.getColumns().addAll(serviceNameColumn, serviceUsernameColumn, servicePasswordColumn, serviceRegistrationDateColumn);

        pmTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        pmTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pmTable.setItems(App.getInstance().getDataManager().getPasswordStorage().getRecords());
    }
}
