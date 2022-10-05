package me.xurround.mlock.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.xurround.mlock.model.ServiceField;

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
        TableColumn<ServiceField, String> serviceNameColumn = new TableColumn<ServiceField, String>("Service");
        serviceNameColumn.setCellValueFactory(new PropertyValueFactory<ServiceField, String>("serviceName"));
        TableColumn<ServiceField, String> serviceUsernameColumn = new TableColumn<ServiceField, String>("Username");
        serviceUsernameColumn.setCellValueFactory(new PropertyValueFactory<ServiceField, String>("serviceUsername"));
        TableColumn<ServiceField, String> servicePasswordColumn = new TableColumn<ServiceField, String>("Password");
        servicePasswordColumn.setCellValueFactory(new PropertyValueFactory<ServiceField, String>("servicePassword"));
        TableColumn<ServiceField, Integer> serviceRegistrationDateColumn = new TableColumn<ServiceField, Integer>("Registration Date");
        serviceRegistrationDateColumn.setCellValueFactory(new PropertyValueFactory<ServiceField, Integer>("serviceRegistrationDate"));

        pmTable.getColumns().addAll(serviceNameColumn, serviceUsernameColumn, servicePasswordColumn, serviceRegistrationDateColumn);

        ObservableList<ServiceField> services = FXCollections.observableArrayList(
                new ServiceField("YouTube", "qwegbsf", "adgasfg", new Date()),
                new ServiceField("Twitter", "qwegbsfDsag", "adgasfg", new Date()),
                new ServiceField("Facebook", "123gagra", "adgasfg", new Date())
        );
        pmTable.setColumnResizePolicy(cb -> true);
        pmTable.setItems(services);
    }
}
