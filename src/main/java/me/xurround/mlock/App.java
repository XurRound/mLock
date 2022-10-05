package me.xurround.mlock;

import javafx.application.Application;
import javafx.stage.Stage;
import me.xurround.mlock.layout.SceneManager;
import me.xurround.mlock.misc.LayoutLoader;
import me.xurround.mlock.settings.Localization;

import java.io.IOException;

public class App extends Application
{
    private SceneManager sceneManager;

    private static App instance;

    public App()
    {
        instance = this;
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        System.setProperty("prism.lcdtext", "false");
        sceneManager = new SceneManager(stage, 800, 500, LayoutLoader.load("splash"));
        sceneManager.addLayout("login", "main", "register", "splash");
        stage.setTitle(Localization.getLocalizedString("title_pass_manager"));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

    public static App getInstance()
    {
        return instance;
    }

    public SceneManager getSceneManager()
    {
        return sceneManager;
    }
}