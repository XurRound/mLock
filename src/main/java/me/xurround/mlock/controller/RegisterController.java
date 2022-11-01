package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import me.xurround.mlock.App;
import me.xurround.mlock.misc.IOHelper;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.model.Preferences;
import me.xurround.mlock.model.Profile;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;

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

    @FXML
    private Button cancelBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        storagePathTF.setText(IOHelper.getWorkingDirectoryPath().toString());

        changeStoragePathBtn.setOnMouseClicked(e ->
        {
            DirectoryChooser dirChooser = new DirectoryChooser();
            File selectedDir = dirChooser.showDialog(storagePathTF.getScene().getWindow());
            if (selectedDir != null)
                storagePathTF.setText(selectedDir.getAbsolutePath());
        });

        Preferences preferences = App.getInstance().getDataManager().getPreferences();

        profileNameTF.textProperty().addListener((obs, oldValue, newValue) ->
        {
            storagePathTF.setText(Path.of(IOHelper.getWorkingDirectoryPath().toString(), newValue.toLowerCase()).toString());
        });

        registerProfileBtn.setOnAction(e ->
        {
            if (profileNameTF.getText().isEmpty() ||
                storagePathTF.getText().isEmpty() ||
                !masterPasswordPF.getText().equals(confirmPasswordPF.getText()) ||
                masterPasswordPF.getText().isEmpty())
                return;
            Profile profile = new Profile(profileNameTF.getText(), storagePathTF.getText());
            App.getInstance().getDataManager().getPreferences().getProfiles().add(profile);
            preferences.setCurrentProfile(profile.getProfileName());
            App.getInstance().getSceneManager().setLayout(AppScene.LOGIN, TransitionType.SLIDE_RIGHT);
        });

        cancelBT.setDisable(App.getInstance().getDataManager().getPreferences().getCurrentProfile().equals(Profile.getDefault()));
        cancelBT.setOnMouseClicked(e ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.PROFILE_SELECT, TransitionType.SLIDE_RIGHT);
        });
    }
}
