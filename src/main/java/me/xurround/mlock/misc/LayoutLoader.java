package me.xurround.mlock.misc;

import javafx.fxml.FXMLLoader;
import me.xurround.mlock.App;

import java.io.IOException;

public class LayoutLoader
{
    public static <T> T load(String name) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/me/xurround/mlock/layout/" + name + ".fxml"));
        return fxmlLoader.load();
    }
}
