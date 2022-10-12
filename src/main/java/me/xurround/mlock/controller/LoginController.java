package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import me.xurround.mlock.App;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.settings.Localization;
import me.xurround.mlock.misc.enums.TransitionType;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    private Label loginText;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Hyperlink forgotPasswordHL;

    @FXML
    private Circle avatarCL;

    @FXML
    private Label avatarLB;

    @FXML
    private Label profileName;

    @FXML
    private Hyperlink changeProfileHL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loginText.setText(Localization.getLocalizedString("login_text"));

        loginBtn.setOnMouseClicked(mouseEvent ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.SPLASH, TransitionType.SLIDE_RIGHT);
        });

        forgotPasswordHL.setOnMouseClicked(e -> forgotPasswordHL.setVisited(false));
        changeProfileHL.setOnMouseClicked(e -> changeProfileHL.setVisited(false));
    }
}
