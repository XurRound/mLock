package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.xurround.mlock.App;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.model.Profile;

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
    private Label profileNameLB;

    @FXML
    private Hyperlink changeProfileHL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loginText.setText(App.getInstance().getLocalizationManager().getLocalizedString("login_text"));

        Profile currentProfile = App.getInstance().getDataManager().getPreferences().getCurrentProfile();

        profileNameLB.setText(currentProfile.getProfileName());
        avatarLB.setText(currentProfile.getProfileName().substring(0, 1).toUpperCase());

        loginBtn.setOnAction(e ->
        {
            if (passwordPF.getText().isEmpty())
                return;
            //TODO: try to decrypt data storage, if ok -> login user
            App.getInstance().getSceneManager().setLayout(AppScene.MAIN, TransitionType.SLIDE_RIGHT);
        });

        forgotPasswordHL.setOnMouseClicked(e ->
        {
            forgotPasswordHL.setVisited(false);
            Alert forgotPasswordAlert = new Alert(Alert.AlertType.INFORMATION);
            forgotPasswordAlert.setTitle("Forgotten password");
            forgotPasswordAlert.setHeaderText("Bad news");
            forgotPasswordAlert.setContentText("There is no ability to restore a forgotten password now. We're working hard to add this feature. :)");
            forgotPasswordAlert.show();
        });
        changeProfileHL.setOnMouseClicked(e ->
        {
            changeProfileHL.setVisited(false);
            App.getInstance().getSceneManager().setLayout(AppScene.PROFILE_SELECT, TransitionType.SLIDE_RIGHT);
        });
    }
}
