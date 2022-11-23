package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.skin.TextFieldSkin;
import me.xurround.mlock.App;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.misc.skin.PasswordTextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AccountAddController implements Initializable
{
    @FXML
    private TextField serviceNameTF;

    @FXML
    private TextField usernameTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private CheckBox showPasswordCB;

    @FXML
    private DatePicker regDateDP;

    @FXML
    private Button accountAddBT;

    @FXML
    private Button cancelBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        passwordTF.setSkin(new PasswordTextField(passwordTF));
        regDateDP.setValue(LocalDate.now());
        showPasswordCB.setOnMouseClicked(e ->
        {
            if (!showPasswordCB.isSelected())
                passwordTF.setSkin(new PasswordTextField(passwordTF));
            else
                passwordTF.setSkin(new TextFieldSkin(passwordTF));
        });
        accountAddBT.setOnAction(e ->
        {
            if (serviceNameTF.getText().isEmpty() ||
                usernameTF.getText().isEmpty() ||
                passwordTF.getText().isEmpty() ||
                regDateDP.getValue() == null)
                return;
            App.getInstance().getDataManager().getPasswordStorage().addAccount(
                serviceNameTF.getText(), usernameTF.getText(), passwordTF.getText(), regDateDP.getValue());
            App.getInstance().getSceneManager().setLayout(AppScene.MAIN, TransitionType.FADE);
        });
        cancelBT.setOnAction(evt ->
                App.getInstance().getSceneManager().setLayout(AppScene.MAIN, TransitionType.SLIDE_LEFT));
    }
}
