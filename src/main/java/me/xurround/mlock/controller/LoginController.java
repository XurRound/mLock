package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.xurround.mlock.App;
import javafx.fxml.Initializable;
import me.xurround.mlock.misc.NotifyHelper;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.model.Profile;
import me.xurround.mlock.settings.LocalizationManager;

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
    private Label avatarLB;

    @FXML
    private Label profileNameLB;

    @FXML
    private Hyperlink changeProfileHL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        LocalizationManager localizationManager = App.getInstance().getLocalizationManager();

        loginText.setText(localizationManager.getLocalizedString("login_text"));

        Profile currentProfile = App.getInstance().getDataManager().getPreferences().getCurrentProfile();

        profileNameLB.setText(currentProfile.getProfileName());
        avatarLB.setText(currentProfile.getProfileName().substring(0, 1).toUpperCase());

        loginBtn.setOnAction(e ->
        {
            if (passwordPF.getText().isEmpty())
                return;
            if (App.getInstance().getDataManager().loadPasswordStorage(passwordPF.getText()))
                App.getInstance().getSceneManager().setLayout(AppScene.MAIN, TransitionType.SLIDE_RIGHT);
            else
            {
                NotifyHelper.notifyAlert(
                    Alert.AlertType.ERROR,
                    localizationManager.getLocalizedString("invalid_password"),
                    localizationManager.getLocalizedString("invalid_password"),
                    localizationManager.getLocalizedString("invalid_password_text")
                );
            }
        });

        forgotPasswordHL.setOnMouseClicked(e ->
        {
            forgotPasswordHL.setVisited(false);
            NotifyHelper.notifyAlert(
                Alert.AlertType.ERROR,
                localizationManager.getLocalizedString("forgotten_password"),
                localizationManager.getLocalizedString("bad_news"),
                localizationManager.getLocalizedString("forgotten_password_text")
            );
        });
        changeProfileHL.setOnMouseClicked(e ->
        {
            changeProfileHL.setVisited(false);
            App.getInstance().getSceneManager().setLayout(AppScene.PROFILE_SELECT, TransitionType.SLIDE_RIGHT);
        });
    }
}
