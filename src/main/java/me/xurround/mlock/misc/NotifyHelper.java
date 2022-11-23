package me.xurround.mlock.misc;

import javafx.scene.control.Alert;

public class NotifyHelper
{
    public static void notifyAlert(Alert.AlertType type, String title, String header, String content)
    {
        Alert forgotPasswordAlert = new Alert(type);
        forgotPasswordAlert.setTitle(title);
        forgotPasswordAlert.setHeaderText(header);
        forgotPasswordAlert.setContentText(content);
        forgotPasswordAlert.show();
    }
}
