package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.xurround.mlock.App;
import me.xurround.mlock.misc.IOHelper;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable
{
    @FXML
    private TextField profileNameTF;

    @FXML
    private PasswordField masterPasswordPF;

    @FXML
    private PasswordField confirmPasswordPF;

    @FXML
    private TextField storagePathTF;

    @FXML
    private Button changeStoragePathBtn;

    @FXML
    private Button registerProfileBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        storagePathTF.setText(IOHelper.getWorkingDirectoryPath().toString());

        registerProfileBtn.setOnMouseClicked(mouseEvent ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.MAIN, TransitionType.SLIDE_RIGHT);
        });
    }
}
