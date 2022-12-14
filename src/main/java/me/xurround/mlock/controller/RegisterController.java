package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import me.xurround.mlock.App;
import me.xurround.mlock.misc.IOHelper;
import me.xurround.mlock.misc.NotifyHelper;
import me.xurround.mlock.misc.RandomHelper;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.RegisterState;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.model.Preferences;
import me.xurround.mlock.model.Profile;
import me.xurround.mlock.settings.LocalizationManager;

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
        LocalizationManager localizationManager = App.getInstance().getLocalizationManager();

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
            storagePathTF.setText(Path.of(IOHelper.getWorkingDirectoryPath().toString(), RandomHelper.generateRandomHexString(16)).toString());
        });

        registerProfileBtn.setOnAction(e ->
        {
            if (profileNameTF.getText().isEmpty() ||
                storagePathTF.getText().isEmpty() ||
                !masterPasswordPF.getText().equals(confirmPasswordPF.getText()) ||
                masterPasswordPF.getText().isEmpty())
            {
                NotifyHelper.notifyAlert(Alert.AlertType.ERROR,
                    localizationManager.getLocalizedString("error_title"),
                    localizationManager.getLocalizedString("input_data_incorrect"),
                    localizationManager.getLocalizedString("bad_input"));
                return;
            }
            RegisterState registerState = App.getInstance().getProfileManager().register(profileNameTF.getText(), storagePathTF.getText(), masterPasswordPF.getText());
            switch (registerState)
            {
                case OK:
                    App.getInstance().getSceneManager().setLayout(AppScene.LOGIN, TransitionType.SLIDE_RIGHT);
                    break;
                case USER_EXISTS:
                    NotifyHelper.notifyAlert(Alert.AlertType.ERROR,
                        localizationManager.getLocalizedString("error_title"),
                        localizationManager.getLocalizedString("user_exists"),
                        localizationManager.getLocalizedString("user_exists_text"));
                    break;
                case USER_DIRECTORY_EXISTS:
                    NotifyHelper.notifyAlert(Alert.AlertType.ERROR,
                        localizationManager.getLocalizedString("error_title"),
                        localizationManager.getLocalizedString("user_folder_exists"),
                        localizationManager.getLocalizedString("user_folder_exists_text"));
                    break;
                case WEAK_PASSWORD:
                    NotifyHelper.notifyAlert(Alert.AlertType.ERROR,
                        localizationManager.getLocalizedString("error_title"),
                        localizationManager.getLocalizedString("weak_password"),
                        localizationManager.getLocalizedString("weak_password_text"));
                    break;
            }
        });

        cancelBT.setDisable(App.getInstance().getDataManager().getPreferences().getCurrentProfile().equals(Profile.getDefault()));
        cancelBT.setOnMouseClicked(e ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.PROFILE_SELECT, TransitionType.SLIDE_RIGHT);
        });
    }
}
