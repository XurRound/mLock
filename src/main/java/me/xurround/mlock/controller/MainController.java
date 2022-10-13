package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.xurround.mlock.model.ServiceField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private TableView<ServiceField> pmTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        TableColumn<ServiceField, String> serviceNameColumn = new TableColumn<>("Service");
        serviceNameColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        TableColumn<ServiceField, String> serviceUsernameColumn = new TableColumn<>("Username");
        serviceUsernameColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        serviceUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceUsername"));
        TableColumn<ServiceField, String> servicePasswordColumn = new TableColumn<>("Password");
        servicePasswordColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25));
        servicePasswordColumn.setCellValueFactory(new PropertyValueFactory<>("servicePassword"));
        TableColumn<ServiceField, Integer> serviceRegistrationDateColumn = new TableColumn<>("Registration Date");
        serviceRegistrationDateColumn.prefWidthProperty().bind(pmTable.widthProperty().multiply(0.25).subtract(2));
        serviceRegistrationDateColumn.setCellValueFactory(new PropertyValueFactory<>("serviceRegistrationDate"));

        pmTable.getColumns().addAll(serviceNameColumn, serviceUsernameColumn, servicePasswordColumn, serviceRegistrationDateColumn);

        ObservableList<ServiceField> services = FXCollections.observableArrayList(
                new ServiceField("YouTube", "qwegbsf", "adgasfg", new Date()),
                new ServiceField("Twitter", "qwegbsfDsag", "adgasfg", new Date()),
                new ServiceField("Facebook", "123gagra", "adgasfg", new Date())
        );

        pmTable.getSelectionModel().setCellSelectionEnabled(true);

        pmTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pmTable.setItems(services);
    }
}
