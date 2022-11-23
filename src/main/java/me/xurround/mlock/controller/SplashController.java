package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import me.xurround.mlock.App;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable
{

    @FXML
    private Button registerBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        registerBtn.setOnMouseClicked(mouseEvent ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.REGISTER, TransitionType.SLIDE_RIGHT);
        });
    }
}
