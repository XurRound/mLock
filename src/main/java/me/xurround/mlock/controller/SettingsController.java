package me.xurround.mlock.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import me.xurround.mlock.App;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.Language;
import me.xurround.mlock.misc.enums.TransitionType;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SettingsController implements Initializable
{
    @FXML
    private ComboBox<Language> languageCB;

    @FXML
    private Button applyBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        languageCB.setItems(FXCollections.observableList(Arrays.asList(Language.class.getEnumConstants())));

        languageCB.getSelectionModel().selectedItemProperty().addListener((e, o, n) ->
        {
            App.getInstance().getDataManager().getPreferences().setLanguage(n);
        });

        applyBT.setOnAction(e ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.LOGIN, TransitionType.SLIDE_LEFT);
            App.getInstance().updateTitle();
        });
    }
}
