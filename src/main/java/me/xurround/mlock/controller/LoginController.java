package me.xurround.mlock.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import me.xurround.mlock.App;
import javafx.fxml.Initializable;
import me.xurround.mlock.misc.NotifyHelper;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.AuthState;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.model.Profile;
import me.xurround.mlock.settings.LocalizationManager;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    private Label loginText;

    @FXML
    private Button loginBtn;

    @FXML
    private Button settingsBtn;

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

        EventHandler<ActionEvent> onLoginEvent = (actionEvent ->
        {
            AuthState authState = App.getInstance().getProfileManager().authorize(App.getInstance().getDataManager().getPreferences().getCurrentProfile(), passwordPF.getText());
            switch (authState)
            {
                case OK:
                    App.getInstance().getSceneManager().setLayout(AppScene.MAIN, TransitionType.SLIDE_RIGHT);
                    break;
                case INVALID_PASSWORD:
                    NotifyHelper.notifyAlert(
                        Alert.AlertType.ERROR,
                        localizationManager.getLocalizedString("invalid_password"),
                        localizationManager.getLocalizedString("invalid_password"),
                        localizationManager.getLocalizedString("invalid_password_text")
                    );
                    break;
            }
        });

        ImageView cogView = new ImageView(new Image(Objects.requireNonNull(App.class.getResourceAsStream("img/cog.png"))));
        cogView.setFitHeight(16);
        cogView.setFitWidth(16);
        settingsBtn.setGraphic(cogView);

        settingsBtn.setOnMouseClicked(e ->
        {
            App.getInstance().getSceneManager().setLayout(AppScene.SETTINGS, TransitionType.SLIDE_RIGHT);
        });

        loginBtn.setOnAction(onLoginEvent);
        passwordPF.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ENTER)
                onLoginEvent.handle(null);
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
