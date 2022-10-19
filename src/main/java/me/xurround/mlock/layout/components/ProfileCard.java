package me.xurround.mlock.layout.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import me.xurround.mlock.App;
import me.xurround.mlock.interfaces.listeners.OnProfileSelectListener;
import me.xurround.mlock.model.Profile;

import java.io.IOException;

public class ProfileCard extends HBox
{
    @FXML
    private Circle avatarCL;

    @FXML
    private Label avatarLB;

    @FXML
    private Label profileNameLB;

    private OnProfileSelectListener onProfileSelectListener;

    public ProfileCard(Profile profile)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/me/xurround/mlock/layout/components/profile_card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);

        try
        {
            fxmlLoader.load();
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }

        final double MAX_TEXT_WIDTH = 57;
        final double fontSize = 16;
        Font defaultFont = Font.loadFont(App.class.getResourceAsStream("/me/xurround/mlock/font/whitney-medium.ttf"), fontSize);

        profileNameLB.textProperty().addListener((observableValue, oldValue, newValue) ->
        {
            Text tmpText = new Text(newValue);
            double textWidth = tmpText.getLayoutBounds().getWidth();
            if (textWidth <= MAX_TEXT_WIDTH)
                profileNameLB.setFont(defaultFont);
            else
            {
                double newFontSize = fontSize * MAX_TEXT_WIDTH / textWidth;
                profileNameLB.setStyle("-fx-font-size: " + String.valueOf(newFontSize) + ";");
            }
        });

        profileNameLB.setText(profile.getProfileName());
        avatarLB.setText(profile.getProfileName().substring(0, 1).toUpperCase());

        setOnMouseClicked(e ->
        {
            if (onProfileSelectListener != null)
                onProfileSelectListener.onSelect(profile);
        });
    }

    public void setOnProfileSelectListener(OnProfileSelectListener onProfileSelectListener)
    {
        this.onProfileSelectListener = onProfileSelectListener;
    }
}
