package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.xurround.mlock.App;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.model.AccountRecord;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private TableView<AccountRecord> pmTable;

    @FXML
    private Label addBT;

    @FXML
    private Label removeBT;

    @FXML
    private Label editBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        TableColumn<AccountRecord, String> serviceNameColumn = new TableColumn<>("Service");
        serviceNameColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        TableColumn<AccountRecord, String> serviceUsernameColumn = new TableColumn<>("Username");
        serviceUsernameColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        serviceUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceUsername"));
        TableColumn<AccountRecord, String> servicePasswordColumn = new TableColumn<>("Password");
        servicePasswordColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        servicePasswordColumn.setCellValueFactory(new PropertyValueFactory<>("servicePassword"));
        TableColumn<AccountRecord, Integer> serviceRegistrationDateColumn = new TableColumn<>("Registration Date");
        serviceRegistrationDateColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25).subtract(2));
        serviceRegistrationDateColumn.setCellValueFactory(new PropertyValueFactory<>("serviceRegistrationDate"));

        pmTable.getColumns().addAll(serviceNameColumn, serviceUsernameColumn, servicePasswordColumn, serviceRegistrationDateColumn);

        ObservableList<AccountRecord> services = FXCollections.observableArrayList(
                new AccountRecord("YouTube", "qwegbsf", "adgasfg", new Date()),
                new AccountRecord("Twitter", "qwegbsfDsag", "adgasfg", new Date()),
                new AccountRecord("Facebook", "123gagra", "adgasfg", new Date())
        );

        pmTable.getSelectionModel().setCellSelectionEnabled(true);

        pmTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pmTable.setItems(services);

        addBT.setOnMouseClicked(e ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.ACCOUNT_ADD, TransitionType.FADE);
        });
    }
}
