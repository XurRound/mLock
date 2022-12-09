package me.xurround.mlock.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.skin.TextFieldSkin;
import me.xurround.mlock.App;
import me.xurround.mlock.layout.components.AutocompletionTextField;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.misc.skin.PasswordTextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class AccountAddController implements Initializable
{
    @FXML
    private AutocompletionTextField serviceNameTF;

    @FXML
    private AutocompletionTextField usernameTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private CheckBox showPasswordCB;

    @FXML
    private DatePicker regDateDP;

    @FXML
    private Button accountAddBT;

    @FXML
    private Button generateBT;

    @FXML
    private Button cancelBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        serviceNameTF.getEntries().addAll(Set.of("Google", "Facebook", "Twitter", "LinkedIn", "YouTube", "Instagram", "Gmail", "Yandex", "VK", "SoundCloud", "Spotify", "Twitch"));
        usernameTF.getEntries().add(App.getInstance().getProfileManager().getProfile().getProfileName());
        passwordTF.setSkin(new PasswordTextField(passwordTF));
        regDateDP.setValue(LocalDate.now());
        showPasswordCB.setOnMouseClicked(e ->
        {
            if (!showPasswordCB.isSelected())
                passwordTF.setSkin(new PasswordTextField(passwordTF));
            else
                passwordTF.setSkin(new TextFieldSkin(passwordTF));
        });
        generateBT.setOnMouseClicked(e ->
        {
            String password = new Random().ints(10, 33, 122)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            passwordTF.setText(password);
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
