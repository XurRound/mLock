package me.xurround.mlock.controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import me.xurround.mlock.App;
import me.xurround.mlock.interfaces.listeners.OnProfileSelectListener;
import me.xurround.mlock.layout.components.ProfileCard;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;
import me.xurround.mlock.model.Profile;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileSelectController implements Initializable
{
    @FXML
    private GridPane profilesContainer;

    @FXML
    private Button registerBT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        registerBT.setOnMouseClicked(e -> App.getInstance().getSceneManager().setLayout(AppScene.REGISTER, TransitionType.SLIDE_RIGHT));

        loadProfiles();
    }

    private void loadProfiles()
    {
        int row = 0;
        int col = 0;
        for (Profile profile : App.getInstance().getDataManager().getPreferences().getProfiles())
        {
            if (profile.equals(Profile.getDefault()))
                continue;
            ProfileCard card = new ProfileCard(profile);
            profilesContainer.add(card, col, row);
            card.setOnProfileSelectListener(profileSelectionListener);
            col++;
            if (col > 2)
            {
                col = 0;
                row++;
            }
        }
    }

    private final OnProfileSelectListener profileSelectionListener = (profile ->
    {
        App.getInstance().getDataManager().getPreferences().setCurrentProfile(profile.getProfileName());
        App.getInstance().getSceneManager().setLayout(AppScene.LOGIN, TransitionType.SLIDE_RIGHT);
    });
}
